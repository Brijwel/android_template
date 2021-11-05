package com.brijwel.androidtemplate.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.annotation.*
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.brijwel.androidtemplate.App
import com.brijwel.androidtemplate.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

class CodeSnippet @Inject constructor(private val application: App) {

    fun getApplication(): App = application

    fun getString(@StringRes id: Int): String = application.getString(id)

    fun getString(@StringRes id: Int, vararg formatArgs: Any): String =
        application.getString(id, formatArgs)

    fun getQuantityString(@PluralsRes id: Int, quantity: Int) =
        application.resources.getQuantityString(id, quantity)

    fun getQuantityString(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any) =
        application.resources.getQuantityString(id, quantity, formatArgs)

    fun getStringArray(@ArrayRes id: Int): Array<out String> =
        application.resources.getStringArray(id)

    fun getBoolean(@BoolRes id: Int): Boolean = application.resources.getBoolean(id)

    fun getDrawable(@DrawableRes id: Int): Drawable =
        ContextCompat.getDrawable(application, id) ?: throw Throwable("No such drawable found")

    fun getDimension(@DimenRes id: Int) = application.resources.getDimension(id)

    fun getDimenAsDp(@DimenRes id: Int) =
        (application.resources.getDimension(id) / application.resources.displayMetrics.density).toInt()

    fun getDimenAsSp(@DimenRes id: Int) =
        (application.resources.getDimension(id) / application.resources.displayMetrics.scaledDensity).toInt()

    @ColorInt
    fun getColor(@ColorRes id: Int): Int = ContextCompat.getColor(application, id)

    fun showToast(message: String) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }

    fun showToast(@StringRes id: Int) {
        Toast.makeText(application, id, Toast.LENGTH_SHORT).show()
    }

    fun hasPermission(permission: String): Boolean = ContextCompat.checkSelfPermission(
        application,
        permission
    ) == PackageManager.PERMISSION_GRANTED

    fun hasPermissions(permissions: Array<String>): Boolean = permissions.all { permission ->
        ActivityCompat.checkSelfPermission(
            application,
            permission
        ) != PackageManager.PERMISSION_GRANTED
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                // for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                // for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

    fun showDialog(context: Context, layoutId: Int, dialog: (Dialog) -> Unit) {
        val d = Dialog(context)
        d.requestWindowFeature(Window.FEATURE_NO_TITLE)
        d.setCancelable(true)
        d.setContentView(layoutId)
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        d.window?.setLayout(width, height)
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        d.show()
        dialog(d)
    }

    fun showDatePicker(
        context: Context,
        selectedDate: (LocalDate) -> Unit,
        currentDate: LocalDate = LocalDate.now(),
        dialog: ((DatePickerDialog) -> Unit)? = null
    ) {
        val datePicker = DatePickerDialog(
            context,
            { _, year, month, day ->
                selectedDate(LocalDate.of(year, month.plus(1), day))
            },
            currentDate.year,
            currentDate.monthValue.minus(1),
            currentDate.dayOfMonth
        )
        dialog?.invoke(datePicker)
        datePicker.show()
    }

    fun showTimePicker(
        context: Context,
        selectedTime: (LocalTime) -> Unit,
        currentTime: LocalTime = LocalTime.now(),
        dialog: ((TimePickerDialog) -> Unit)? = null
    ) {
        val timePicker = TimePickerDialog(
            context,
            { _, hourOfDay, minuteOfHour ->
                selectedTime(LocalTime.of(hourOfDay, minuteOfHour))
            },
            currentTime.hour,
            currentTime.minute,
            false
        )
        dialog?.invoke(timePicker)
        timePicker.show()
    }

    fun getMultipartData(filePath: String, param: String): MultipartBody.Part {
        val file = File(filePath)
        return MultipartBody.Part.createFormData(
            param,
            file.name,
            file.asRequestBody(APPLICATION_JSON.toMediaTypeOrNull())
        )
    }

    fun openWebsiteUrl(context: Context, url: String) {
        if (url.isBlank()) {
            return
        }
        openWebsiteUri(context, Uri.parse(url))
    }

    private fun openWebsiteUri(context: Context, uri: Uri) {
        if (context.isChromeCustomTabsSupported()) {
            val intentBuilder = CustomTabsIntent.Builder()
            val params = CustomTabColorSchemeParams.Builder()
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .build()
            intentBuilder.setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_SYSTEM, params)
            intentBuilder.setExitAnimations(
                context,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            intentBuilder.build().launchUrl(context, uri)
        } else {
            context.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun Context.isChromeCustomTabsSupported(): Boolean {
        val serviceIntent = Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION)
        serviceIntent.setPackage(CHROME_PACKAGE)
        val resolveInfo = packageManager.queryIntentServices(serviceIntent, 0)
        return !resolveInfo.isNullOrEmpty()
    }

    companion object {
        private const val CHROME_PACKAGE = "com.android.chrome"
        private const val APPLICATION_JSON = "application/json"
    }
}

# Lt2httpService

A service that executes binaries for [Kodi](https://github.com/xbmc/xbmc)'s addon [lt2http](https://github.com/ElementumOrg/service.lt2http) on [Android](https://www.android.com/) without a [W^X violation](https://developer.android.com/about/versions/10/behavior-changes-10#execute-permission).

## Download

See the [Releases](https://github.com/StefanIlchev/Lt2httpService/releases) page.

## Install

Install an `.apk` with an [Application Binary Interface](https://en.wikipedia.org/wiki/Application_binary_interface) (ABI) that your device supports or the bigger universal one.

Add [lt2http](https://github.com/ElementumOrg/service.lt2http) to [Kodi](https://github.com/xbmc/xbmc) from the `.android_client.zip`. When the addon completes installation it'll start the service app which may ask you for some [Android permissions](https://support.google.com/googleplay/answer/6270602).

On [Android 11](https://developer.android.com/about/versions/11/privacy/storage#all-files-access) and up the service app may ask you to allow it to manage all files or try to open its settings if your device [doesn't let it](https://issuetracker.google.com/issues/71327396#comment5). If nothing else works the service app will show you the following command that will allow it to manage all files which you might have to execute for [lt2http](https://github.com/ElementumOrg/service.lt2http) to work properly:

```bat
adb shell appops set --uid service.lt2http.android MANAGE_EXTERNAL_STORAGE allow
```

The service app won't work if it's a different version from the addon and will show a message like:

`<service-version> ≠ <client-version>`

## Build

Prepare an [lt2http](https://github.com/ElementumOrg/service.lt2http) addon `.zip` at `path/to/service.lt2http-<version>.zip` and a binaries `.zip` at `path/to/lt2http-binaries-<version>.zip` containing [Android](https://www.android.com/) binaries for your device either by [building the lt2http project](https://github.com/ElementumOrg/service.lt2http#build) or by downloading it from a [release's](https://github.com/ElementumOrg/service.lt2http/releases) assets.

Assuming you already have [Android Studio](https://developer.android.com/studio) setup, execute the following command in a terminal:

```bat
gradlew clean assembleRelease zipAndroidClient -DaddonZip="path/to/service.lt2http-<version>.zip" -DbinariesZip="path/to/lt2http-binaries-<version>.zip"
```

and when it completes successfully look for the following files in the newly created `build` folder:

1. `service.lt2http-<version>.android_client.zip` found directly in the `build` folder is the addon `.zip` you provided with scripts modified to work with the service app;

2. `Lt2httpService-<ABI>-release-<version>.apk` found in the `build/outputs/apk/release` subfolder are one or more `.apk` files containing the binaries from the binaries `.zip` you provided.

## Notes

From now on when [lt2http](https://github.com/ElementumOrg/service.lt2http) starts its daemon it'll actually start the service app and when it stops it'll stop the service app.

You'll no longer be able to use paths to [Kodi](https://github.com/xbmc/xbmc)'s data folder as they will be translated to the service app's data folder.

The output from the binaries will no longer appear in [Kodi's log](https://kodi.wiki/view/Log_file) and will go to [Android's log](https://developer.android.com/studio/command-line/logcat) instead.

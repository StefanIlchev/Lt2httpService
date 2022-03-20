package service.lt2http.android;

import android.net.Uri;

import ilchev.stefan.binarywrapper.BaseForegroundService;

public class ForegroundService extends BaseForegroundService {

	@Override
	protected Class<?> getMainActivityClass() {
		return MainActivity.class;
	}

	@Override
	protected DaemonRunnable getDaemonRunnable(Uri data) {
		var fragment = data != null ? data.getFragment() : null;
		return new DaemonRunnable(this, fragment != null ? fragment.split("\0") : null);
	}

	@Override
	protected String getVersionName(Uri data) {
		return data != null ? data.getSchemeSpecificPart() : null;
	}
}

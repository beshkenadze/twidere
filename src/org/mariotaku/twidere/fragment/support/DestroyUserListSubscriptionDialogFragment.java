package org.mariotaku.twidere.fragment.support;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import org.mariotaku.twidere.R;
import org.mariotaku.twidere.model.ParcelableUserList;
import org.mariotaku.twidere.util.AsyncTwitterWrapper;
import org.mariotaku.twidere.util.ThemeUtils;

public class DestroyUserListSubscriptionDialogFragment extends BaseSupportDialogFragment implements
		DialogInterface.OnClickListener {

	public static final String FRAGMENT_TAG = "destroy_user_list";

	@Override
	public void onClick(final DialogInterface dialog, final int which) {
		switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				final ParcelableUserList user_list = getUserList();
				final AsyncTwitterWrapper twitter = getTwitterWrapper();
				if (user_list == null || twitter == null) return;
				twitter.destroyUserListSubscriptionAsync(user_list.account_id, user_list.id);
				break;
			default:
				break;
		}
	}

	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		final Context wrapped = ThemeUtils.getDialogThemedContext(getActivity());
		final AlertDialog.Builder builder = new AlertDialog.Builder(wrapped);
		final ParcelableUserList userList = getUserList();
		if (userList != null) {
			builder.setTitle(getString(R.string.unsubscribe_from_user_list, userList.name));
			builder.setMessage(getString(R.string.unsubscribe_from_user_list_confirm_message, userList.name));
		}
		builder.setPositiveButton(android.R.string.ok, this);
		builder.setNegativeButton(android.R.string.cancel, null);
		return builder.create();
	}

	private ParcelableUserList getUserList() {
		final Bundle args = getArguments();
		if (!args.containsKey(EXTRA_USER_LIST)) return null;
		return args.getParcelable(EXTRA_USER_LIST);
	}

	public static DestroyUserListSubscriptionDialogFragment show(final FragmentManager fm,
			final ParcelableUserList user_list) {
		final Bundle args = new Bundle();
		args.putParcelable(EXTRA_USER_LIST, user_list);
		final DestroyUserListSubscriptionDialogFragment f = new DestroyUserListSubscriptionDialogFragment();
		f.setArguments(args);
		f.show(fm, FRAGMENT_TAG);
		return f;
	}
}

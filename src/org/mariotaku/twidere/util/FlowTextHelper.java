package org.mariotaku.twidere.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.LeadingMarginSpan.LeadingMarginSpan2;
import android.view.View;
import android.widget.TextView;

public class FlowTextHelper {

	public static void flowText(final TextView textView, final View flowedView, final int additionalPadding) {

		// Get height and width of the image and height of the text line
		flowedView.measure(0, 0);
		final int height = flowedView.getMeasuredHeight();
		final int width = flowedView.getMeasuredWidth() + additionalPadding;
		textView.measure(width, height); // to allow getTotalPaddingTop
		final int padding = textView.getTotalPaddingTop();
		final float textLineHeight = textView.getPaint().getTextSize();

		// Set the span according to the number of lines and width of the image
		final int lines = Math.round((height - padding) / textLineHeight);
		final SpannableString ss = SpannableString.valueOf(textView.getText());
		// For an html text you can use this line: SpannableStringBuilder ss =
		// (SpannableStringBuilder)Html.fromHtml(text);
		ss.setSpan(new FlowLeadingMarginSpan2(lines, width), 0, ss.length(), 0);
		textView.setText(ss);

	}

	public static class FlowLeadingMarginSpan2 implements LeadingMarginSpan2 {
		private final int margin;
		private final int lines;

		public FlowLeadingMarginSpan2(final int lines, final int margin) {
			this.margin = margin;
			this.lines = lines;
		}

		@Override
		public void drawLeadingMargin(final Canvas c, final Paint p, final int x, final int dir, final int top,
				final int baseline, final int bottom, final CharSequence text, final int start, final int end,
				final boolean first, final Layout layout) {
		}

		@Override
		public int getLeadingMargin(final boolean first) {
			return first ? margin : 0;
		}

		@Override
		public int getLeadingMarginLineCount() {
			return lines;
		}
	}
}

package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * The Class AddBorder.
 */
public class AddBorder implements Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The border size. */
	private double borderSize;

	/**
	 * Instantiates a new adds the border.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param borderSize
	 *            the border size
	 */
	public AddBorder(Bitmap bitmapIn, double borderSize) {
		this.bitmapIn = bitmapIn;
		this.borderSize = borderSize;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mAddBorder
	 *            the m add border
	 * @return the bitmap
	 */
	@Override
	public Bitmap executeFilter() {

		long time = System.currentTimeMillis();
		Bitmap bitmapOut = null;
		int width = this.getBitmapIn().getWidth();
		int height = this.getBitmapIn().getHeight();
		int x, y, border;
		x = y = 0;
		border = (int) (((width * this.getBorderSize()) + (height * this
				.getBorderSize())) / 2);
		bitmapOut = Bitmap.createBitmap(width, height, this.getBitmapIn()
				.getConfig());
		Canvas canvas = new Canvas();
		canvas.setBitmap(bitmapOut);
		canvas.drawBitmap(this.getBitmapIn(), 0, 0, null);

		Paint paint = new Paint();
		for (x = 0; x < width; x++) {
			for (y = 0; y < height; y++) {
				if (((x < border) || (x > width - border))
						|| ((y < border) || (y > height - border))) {
					paint.setARGB(127, 0, 0, 0);
					canvas.drawPoint(x, y, paint);
				}
				if (((x == border) || (x == width - border))
						|| ((y == border) || (y == height - border))) {
					paint.setARGB(127, 255, 255, 255);
					canvas.drawPoint(x, y, paint);
				}
			}
		}
		time = System.currentTimeMillis() - time;
		System.out.println("Finished @ " + time + "ms");
		
		return bitmapOut;
	}

	/**
	 * Gets the bitmap in.
	 * 
	 * @return the bitmap in
	 */
	public Bitmap getBitmapIn() {
		return bitmapIn;
	}

	/**
	 * Sets the bitmap in.
	 * 
	 * @param bitmapIn
	 *            the new bitmap in
	 */
	public void setBitmapIn(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	/**
	 * Gets the border size.
	 * 
	 * @return the border size
	 */
	public double getBorderSize() {
		return borderSize;
	}

	/**
	 * Sets the border size.
	 * 
	 * @param borderSize
	 *            the new border size
	 */
	public void setBorderSize(double borderSize) {
		this.borderSize = borderSize;
	}
}

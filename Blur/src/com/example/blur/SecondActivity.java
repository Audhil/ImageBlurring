package com.example.blur;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Bitmap result = BlurImage((Bitmap)getIntent().getParcelableExtra("image"));
		
		((ImageView)findViewById(R.id.secondImage)).setImageBitmap(result);
	}
	
	@SuppressLint("NewApi")
	//	http://blog.neteril.org/blog/2013/08/12/blurring-images-on-android/
	private Bitmap BlurImage(Bitmap input) {
		
		RenderScript rsScript = RenderScript.create(getApplicationContext());
		Allocation alloc = Allocation.createFromBitmap(rsScript, input);

		ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rsScript, alloc.getElement());
		blur.setRadius(12);
		blur.setInput(alloc);

		Bitmap result = Bitmap.createBitmap(input.getWidth(), input.getHeight(), input.getConfig ());
		Allocation outAlloc = Allocation.createFromBitmap(rsScript, result);
		blur.forEach(outAlloc);
		outAlloc.copyTo(result);

		rsScript.destroy();
		return result;
	}
}
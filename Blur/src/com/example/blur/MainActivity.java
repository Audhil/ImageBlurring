package com.example.blur;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private RelativeLayout baseLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		baseLayout = (RelativeLayout)findViewById(R.id.container);
		baseLayout.setDrawingCacheEnabled(true);
		
		baseLayout.measure(MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED),MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED));
		
		baseLayout.layout(0, 0, baseLayout.getMeasuredWidth(),baseLayout.getMeasuredHeight());
		
		baseLayout.buildDrawingCache(true);
		
		Bitmap bMap = Bitmap.createBitmap(baseLayout.getDrawingCache());
		
		startActivity(new Intent(this,SecondActivity.class).putExtra("image", bMap));
	}
}
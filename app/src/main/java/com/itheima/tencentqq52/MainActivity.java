package com.itheima.tencentqq52;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.tencentqq52.drag.DragLayout;
import com.itheima.tencentqq52.drag.DragLayout.OnDragStatusChangeListener;
import com.itheima.tencentqq52.drag.MyLinearLayout;
import com.itheima.tencentqq52.util.Cheeses;
import com.itheima.tencentqq52.util.Utils;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import java.util.Random;
/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：TencentQQ52
 * Package_Name：com.itheima.tencentqq52
 * Version：1.0
 * time：2016/2/15 16:40
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class MainActivity extends Activity {

	private static final String TAG = "TAG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		final ListView mLeftList = (ListView) findViewById(R.id.lv_left);
		final ListView mMainList = (ListView) findViewById(R.id.lv_main);
		final ImageView mHeaderImage = (ImageView) findViewById(R.id.iv_header);
		MyLinearLayout mLinearLayout = (MyLinearLayout) findViewById(R.id.mll);
		
		// 查找Draglayout, 设置监听
		DragLayout mDragLayout = (DragLayout) findViewById(R.id.dl);

		// 设置引用
		mLinearLayout.setDraglayout(mDragLayout);
		
		mDragLayout.setDragStatusListener(new OnDragStatusChangeListener() {
			
			@Override
			public void onOpen() {
				Utils.showToast(MainActivity.this, "onOpen");
				// 左面板ListView随机设置一个条目
				Random random = new Random();
				
				int nextInt = random.nextInt(50);
				mLeftList.smoothScrollToPosition(nextInt);
				
			}
			
			@Override
			public void onDraging(float percent) {
				Log.d(TAG, "onDraging: " + percent);// 0 -> 1
				// 更新图标的透明度
				// 1.0 -> 0.0
				ViewHelper.setAlpha(mHeaderImage, 1 - percent);
			}
			
			@Override
			public void onClose() {
				Utils.showToast(MainActivity.this, "onClose");
				// 让图标晃动
//				mHeaderImage.setTranslationX(translationX)
				ObjectAnimator mAnim = ObjectAnimator.ofFloat(mHeaderImage, "translationX", 15.0f);
				mAnim.setInterpolator(new CycleInterpolator(4));
				mAnim.setDuration(500);
				mAnim.start();
			}
		});
		
		mLeftList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView mText = ((TextView)view);
				mText.setTextColor(Color.WHITE);
				return view;
			}
		});
		
		mMainList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cheeses.NAMES));

	}

}

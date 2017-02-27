package com.itheima.tencentqq52.drag;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.itheima.tencentqq52.drag.DragLayout.Status;
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
public class MyLinearLayout extends LinearLayout {

	private DragLayout mDragLayout;

	public MyLinearLayout(Context context) {
		super(context);
	}

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setDraglayout(DragLayout mDragLayout){
		this.mDragLayout = mDragLayout;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// 如果当前是关闭状态, 按之前方法判断
		if(mDragLayout.getStatus() == Status.Close){
			return super.onInterceptTouchEvent(ev);
		}else {
			return true;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 如果当前是关闭状态, 按之前方法处理
		if(mDragLayout.getStatus() == Status.Close){
			return super.onTouchEvent(event);
		}else {
			// 手指抬起, 执行关闭操作
			if(event.getAction() == MotionEvent.ACTION_UP){
				mDragLayout.close();
			}
			return true;
		}
	}

}

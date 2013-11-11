package com.apkez.easylife;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.apkez.easylife.entity.Requester;
import com.apkez.easylife.utils.Constant;
import com.apkez.easylife.utils.NetHelper;
import com.apkez.easylife.utils.ThreadPoolExecutor;
import com.apkez.easylife.utils.ViewUtil;

public abstract class BasicActivity extends Activity implements View.OnClickListener{

	private ThreadPoolExecutor executor;
	protected Context context;
	
	public BasicActivity(){
		executor = ThreadPoolExecutor.getInstance();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*全屏*/
		ViewUtil.hideTitleBar(this);
		ViewUtil.setFullScreen(this, true);
		/*初始化context*/
		context = getApplicationContext();
		/*加载*/
		init();
	}
	
	/*初始化*/
	public void init(){
		initLayout();
		findViewById();
		bindListener();
		process();
	}
	
	/*Handler*/
	static class BasicHandler extends Handler{
		private Requester requester;
		private Context context;
		private DataCallback callBack;
		
		
		public BasicHandler(Requester requester, Context context,
				DataCallback callback) {
			super();
			this.requester = requester;
			this.context = context;
			this.callBack = callback;
		}

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == Constant.SUCCESS) {
				if (msg.obj == null) {
					//show diaolog for net error R.string.net_error
				} else {
					/*传递处理数据*/
					callBack.process(msg.obj, true);
				}
			} else if (msg.what == Constant.NET_ERROR) {
				//show diaolog for net error R.string.net_error
			}
		}
	}
	
	/*Thread*/
	static class BasicThread implements Runnable{
		private Handler handler;
		private Requester requester;
		private Context context;
		
		public BasicThread(Requester requester, Context context, Handler handler){
			this.context = context;
			this.requester = requester;
			this.handler = handler;
		}
		
		@Override
		public void run() {
			Message msg = Message.obtain();
			/*检查网络*/
			if(NetHelper.isAvailable(context)){
				/*向服务器发送请求*/
				msg.what = Constant.SUCCESS;
				msg.obj = NetHelper.post(requester);
			}else{
				msg.what = Constant.NET_ERROR;
				msg.obj = null;
			}
			
			handler.handleMessage(msg);
		}
	}
	
	/*回调处理从服务器获取的数据*/
	public abstract interface DataCallback<T> {
		public abstract void process(T paramObject, boolean paramBoolean);
	}
	
	/*获取服务器数据处理*/
	public void handle(Requester requester, DataCallback<?> callback){
		BasicHandler handler = new BasicHandler(requester, this, callback);
		BasicThread  thread = new BasicThread(requester, this, handler);
		executor.execute(thread);
	}
	
	/*获取VIEW*/
	public abstract void findViewById();
	/*绑定监听器*/
	public abstract void bindListener();
	/*加载布局*/
	public abstract void initLayout();
	/*处理逻辑*/
	public abstract void process();
	
}

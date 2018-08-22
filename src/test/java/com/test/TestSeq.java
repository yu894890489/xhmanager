package com.test;

import com.xh.comm.util.base.Sequence;

public class TestSeq {
	
	
	public static void main(String[] args) {
		System.out.println(34353);
		for (int i = 0; i < 50; i++) {
			
		}
	}
	static Runnable myRunnable = new Runnable(){

		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	};
}

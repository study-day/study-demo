package com.studyday.studythread.testkillpid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class BaseRuntime {

	private Runtime runTime;
	private Process process;
	private StringBuffer allLog;
	private int exitValue = -3;

	public BaseRuntime() {
		this.runTime = Runtime.getRuntime();
		this.allLog = new StringBuffer();
	}
	/**
	 * 使用时方法, Process process = this.getRt().exec(); this.setProcess(process);
	 * exec的参数参考http://tool.oschina.net/apidocs/apidoc?api=jdk-zh
	 * @throws IOException
	 */
	public abstract void runcmd() throws IOException;
	public abstract void wirteLog() throws IOException;
	/**
	 * 执行启动并输出日志,返回命令的返回值
	 * 
	 * @throws Exception
	 */
	public int start()  {

		try {
			runcmd();

		InputStream inPrint = process.getInputStream();
		InputStream inError = process.getErrorStream();
		BufferedReader readPrint = new BufferedReader(new InputStreamReader(inPrint,"utf-8"));
		BufferedReader readErrot = new BufferedReader(new InputStreamReader(inError,"utf-8"));
		String printLog = "";
		String printLine = "";
		while ((printLine = readPrint.readLine()) != null) {
			printLog += printLine+"\n";
		}
		//System.out.println(printLog);

		String errorLog = "";
		String errorLine = "";
		while ((errorLine = readErrot.readLine()) != null) {
			errorLog += errorLine + "\n";
		}
		//System.err.println(errorLog);
	    exitValue = process.waitFor();
	    this.allLog.append(printLog).append("\n").append(errorLog);
		wirteLog();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				exitValue = process.waitFor();//必须写的代码
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
			try {
				exitValue = process.waitFor();//必须写的代码
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		return exitValue;
	}

	public Runtime getRt() {
		return runTime;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public Process getProcess() {
		return process;
	}

	public String getAllLog() {
		return allLog.toString();
	}
}
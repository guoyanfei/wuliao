package com.wuliao.dandan.util.tag.page;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageTag extends SimpleTagSupport {
	private Integer pageCurrent;
	private Integer totalPage;
	private String path;
	private String param;
	private Integer pageSize;

	public PageTag(Integer pageCurrent, Integer totalPage, String path, String param, Integer pageSize) {
		super();
		this.pageCurrent = pageCurrent;
		this.totalPage = totalPage;
		this.path = path;
		this.param = param;
		this.pageSize = pageSize;
	}

	public void doTag() throws IOException {
		JspWriter w = this.getJspContext().getOut();
		StringBuffer sb = new StringBuffer();

		if (this.pageCurrent <= 1) {
			sb.append("首页");
			sb.append("&nbsp;");
			sb.append("上一页");
			sb.append("&nbsp;");
		} else {
			sb.append("<a href=\"");
			sb.append(path);
			if (!(path.substring(path.length() - 1, path.length()).equals("&"))) {
				sb.append("?");
			}
			sb.append(param);
			sb.append("=");
			sb.append("1");
			sb.append("\">");
			sb.append("首页</a>");
			sb.append("&nbsp;&nbsp;");

			sb.append("<a href=\"");
			sb.append(path);
			if (!(path.substring(path.length() - 1, path.length()).equals("&"))) {
				sb.append("?");
			}
			sb.append(param);
			sb.append("=");
			sb.append(this.pageCurrent - 1);
			sb.append("\">");
			sb.append("上一页</a>");
			sb.append("&nbsp;&nbsp;");
		}

		sb.append("&nbsp;&nbsp;<font color='red'>当前 " + this.pageCurrent + " 页/共 " + this.totalPage + " 页</font>&nbsp;&nbsp;");

		if (this.pageCurrent.equals(this.totalPage)) {
			sb.append("下一页");
			sb.append("&nbsp;");
			sb.append("尾页");
			sb.append("&nbsp;");
		} else {
			sb.append("<a href=\"");
			sb.append(path);
			if (!(path.substring(path.length() - 1, path.length()).equals("&"))) {
				sb.append("?");
			}
			sb.append(param);
			sb.append("=");
			sb.append(this.pageCurrent + 1);
			sb.append("\">");
			sb.append("下一页</a>");
			sb.append("&nbsp;&nbsp;");

			sb.append("<a href=\"");
			sb.append(path);
			if (!(path.substring(path.length() - 1, path.length()).equals("&"))) {
				sb.append("?");
			}
			sb.append(param);
			sb.append("=");
			sb.append(this.totalPage);
			sb.append("\">");
			sb.append("尾页</a>");
			sb.append("&nbsp;&nbsp;");
		}

		w.println(sb);
	}

	public PageTag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}

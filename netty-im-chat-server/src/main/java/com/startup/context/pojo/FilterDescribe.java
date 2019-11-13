package com.startup.context.pojo;

/**
 * @author : Kevin
 * @Title : FilterDescribe
 * @ProjectName netty-im-chat
 * @Description : TODO
 * @Time : Created in 2019/11/14 1:39
 * @Modifyed By :
 */
public class FilterDescribe {
    private String filterName;
    private String displayName;
    private String filterClass;
    private String urlPattern;

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }
}

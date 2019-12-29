package com.cornucopia.kotlin.start.idioms;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019/12/29
 */
public class ViewJava {

    private OnClickListenerForJava onClickListenerForJava;

    public void setOnClickListener(OnClickListenerForJava onClickListener){
        this.onClickListenerForJava=onClickListener;
    }

    public void onClick(){
        this.onClickListenerForJava.onClick();
    }

    public void click(){
        this.onClickListenerForJava.onClick();
    }

}

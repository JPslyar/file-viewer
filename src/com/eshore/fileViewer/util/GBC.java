package com.eshore.fileViewer.util;

import java.awt.GridBagConstraints;
import java.awt.Insets;
/**
 * 构造采用GridBagLayout管理组件的容器上的每个组件对应的GridBagConstraints类来限制
 * 组件的大小、位置等特性
 * @author JIANGPENG
 */
public class GBC extends GridBagConstraints {
  public GBC(int x, int y) {
    this.gridx = x;
    this.gridy = y;
  }
  
  /**
   * 设置组件在容器的坐标和组件所占据的行数和列数
   * @param gridx
   * @param gridy
   * @param gridwidth
   * @param gridheight
   */
  public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
    this.gridx = gridx;
    this.gridy = gridy;
    this.gridwidth = gridwidth;
    this.gridheight = gridheight;
  }

  public GBC setAnchor(int anchor) {
    this.anchor = anchor;
    return this;
  }

  /**
   * 设置在容器大小改变的情况下，组件在水平、垂直方向的变化情况
   * @param fill
   * @return
   */
  public GBC setFill(int fill) {
    this.fill = fill;
    return this;
  }

  /**
   * 设置组件所能分配的的列数和行数
   * @param weightx
   * @param weighy
 * @param weighty 
   * @return
   */
  public GBC setWeight(double weightx,  double weighty) {
    this.weightx = weightx;
    this.weighty = weighty;
    return this;
  }

  public GBC setInset(int distance) {
    this.insets = new Insets(distance, distance, distance, distance);
    return this;
  }

  /**
   * 设置组件与其他组件之间的间隔
   * @param top
   * @param left
   * @param bottom
   * @param right
   * @return
   */
  public GBC setInset(int top, int left, int bottom, int right) {
    this.insets = new Insets(top, left, bottom, right);
    return this;
  }

  public GBC setIpad(int ipadx, int ipady) {
    this.ipadx = ipadx;
    this.ipady = ipady;
    return this;
  }
}

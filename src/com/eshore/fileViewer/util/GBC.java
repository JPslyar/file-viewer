package com.eshore.fileViewer.util;

import java.awt.GridBagConstraints;
import java.awt.Insets;
/**
 * �������GridBagLayout��������������ϵ�ÿ�������Ӧ��GridBagConstraints��������
 * ����Ĵ�С��λ�õ�����
 * @author JIANGPENG
 */
public class GBC extends GridBagConstraints {
  public GBC(int x, int y) {
    this.gridx = x;
    this.gridy = y;
  }
  
  /**
   * �������������������������ռ�ݵ�����������
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
   * ������������С�ı������£������ˮƽ����ֱ����ı仯���
   * @param fill
   * @return
   */
  public GBC setFill(int fill) {
    this.fill = fill;
    return this;
  }

  /**
   * ����������ܷ���ĵ�����������
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
   * ����������������֮��ļ��
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

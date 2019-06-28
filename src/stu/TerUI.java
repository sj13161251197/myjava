package stu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TerUI extends JFrame implements ActionListener {
	// 定义组件
	JButton jb1, jb2 = null;
	JPanel jp1, jp2, jp3, jp4 = null;
	JLabel jlb1, jlb2, jlb3, jlb4, jlb5, jlb6 = null;

	public static void main(String[] args) {
		TerUI ui = new TerUI();
	}

	public TerUI() {
		// 创建组件
		jb1 = new JButton("课程管理");
		jb1.setForeground(Color.BLUE);
		jb2 = new JButton("学生名单");
		jb2.setForeground(Color.BLUE);

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		jlb1 = new JLabel("姓名：");
		jlb2 = new JLabel("工号：");
		jlb3 = new JLabel("最新公告：");
		jlb3.setForeground(Color.red);
		jlb4 = new JLabel("我院举行体测的通知");
		jlb5 = new JLabel(new MainUI().tea_name);
		jlb6 = new JLabel(new MainUI().tea_num);

		jp1.add(jlb1);
		jp1.add(jlb5);
		jp1.add(jlb2);
		jp1.add(jlb6);

		jp2.add(jb1);
		jp2.add(jb2);

		jp3.add(jlb3);
		jp3.add(jlb4);

		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.setLayout(new GridLayout(4, 3, 50, 50)); // 设置布局管理器
		this.setTitle("学生成绩管理系统");
		this.setSize(400, 300);
		this.setLocation(200, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {
			dispose();
			new KeChengGuanLiUI(); // 创建一个新界面
		} else if (e.getSource() == jb2) {
			dispose();
			new XueShengMingDanUI(); // 创建一个新界面
		}

	}
}

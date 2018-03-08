package com.njxz.exam.util;

import org.apache.ibatis.annotations.Case;

/*
 * �����׳̶����ɿ����ã�����ֻ������һ�Σ�
 * ������
 * */
public class Constants {
	//difficulty_level�Ѷ�ϵ��ȡֵ
	public static Double DIFFICULTY_LEVEL_VERYEASY=(double) 0.1;
	public static Double DIFFICULTY_LEVEL_EASY=(double) 0.3;
	public static Double DIFFICULTY_LEVEL_MEDIUM=(double) 0.5;
	public static Double DIFFICULTY_LEVEL_HARD=(double) 0.7;
	public static Double DIFFICULTY_LEVEL_VERYHARD=(double) 0.9;
	

	public static double KP_COVERAGE_RATE = 0;//֪ʶ�㸲���� ռ ��Ӧ�� ����
	public static double DIFFICULTY_RATE = 1;//�Ѷ�ϵ��  ռ ��Ӧ�ȱ���
	
	//������Ӧ��
	public static double EXPAND_ADATPER=0.95;
	//����������
	public static int RUN_Count=500;
	
	
	//�ϴ�ͼƬ���λ��
	public static String PHOTO_DIRECTORY_NAME="upload";
	//wordģ����λ��
	public static String WORD_TEMPLETE_DIRECTORY_NAME="wordTemplete";
	
	
	public static String CHINESE_1="һ";
	
	//�õ�1-20֮�����������
	public static String numGetChinese(int i) {
		if(i<=0||i>20) {
			return "";
		}
		switch (i) {
		case 1:
			return "һ";
		case 2:
			return "��";
		case 3:
			return "��";
		case 4:
			return "��";
		case 5:
			return "��";
		case 6:
			return "��";
		case 7:
			return "��";
		case 8:
			return "��";
		case 9:
			return "��";
		case 10:
			return "ʮ";
		case 11:
			return "ʮһ";
		case 12:
			return "ʮ��";
		case 13:
			return "ʮ��";
		case 14:
			return "ʮ��";
		case 15:
			return "ʮ��";
		case 16:
			return "ʮ��";
		case 17:
			return "ʮ��";
		case 18:
			return "ʮ��";
		case 19:
			return "ʮ��";
		case 20:
			return "��ʮ";
		default:
			return "";
		}
		
	}
	
	
	
	//�õ����Ѷȵ�����
	public static String getDiffLevelStrCN(Double level) {
		String levelCN="";
		if((level-DIFFICULTY_LEVEL_VERYEASY)<0.0001&&(level-DIFFICULTY_LEVEL_VERYEASY)>-0.0001) {
			levelCN="������";
		}
		if((level-DIFFICULTY_LEVEL_EASY)<0.0001&&(level-DIFFICULTY_LEVEL_EASY)>-0.0001) {
			levelCN="����";
		}
		if((level-DIFFICULTY_LEVEL_MEDIUM)<0.0001&&(level-DIFFICULTY_LEVEL_MEDIUM)>-0.0001) {
			levelCN="�е�";
		}
		if((level-DIFFICULTY_LEVEL_HARD)<0.0001&&(level-DIFFICULTY_LEVEL_HARD)>-0.0001) {
			levelCN="����";
		}
		if((level-DIFFICULTY_LEVEL_VERYHARD)<0.0001&&(level-DIFFICULTY_LEVEL_VERYHARD)>-0.0001) {
			levelCN="������";
		}
		return levelCN;
	}
	
	public static String getDiffLevelStrEN(Double level) {
		String levelEN="";
		if((level-DIFFICULTY_LEVEL_VERYEASY)<0.0001&&(level-DIFFICULTY_LEVEL_VERYEASY)>-0.0001) {
			levelEN="veryEasy";
		}
		if((level-DIFFICULTY_LEVEL_EASY)<0.0001&&(level-DIFFICULTY_LEVEL_EASY)>-0.0001) {
			levelEN="easy";
		}
		if((level-DIFFICULTY_LEVEL_MEDIUM)<0.0001&&(level-DIFFICULTY_LEVEL_MEDIUM)>-0.0001) {
			levelEN="medium";
		}
		if((level-DIFFICULTY_LEVEL_HARD)<0.0001&&(level-DIFFICULTY_LEVEL_HARD)>-0.0001) {
			levelEN="hard";
		}
		if((level-DIFFICULTY_LEVEL_VERYHARD)<0.0001&&(level-DIFFICULTY_LEVEL_VERYHARD)>-0.0001) {
			levelEN="veryHard";
		}
		return levelEN;
	}
}

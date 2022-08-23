package a01;

import java.util.Scanner;

/**
 * �d�b�ԍ��̍��ڕʕ\���B 
 * 
 * @author �_�E�h�E�J�[��

//���̖��̓��� 
//�v���O���������s����R�}���h�B : "java Tel.java" or "java Tel.java 052-937-0201"
//�v���O���������s���邽�߂̓��� '052-937-0201'
//�s�O�ǔԁF 052
//�s���ǔ�:  937
//�����Ҕԍ��F 0201
*/
public class Tel {

	public static void main(String[] args) 
	{
		String[] arr = null;
		String tele_number = null;
		boolean tester = false;
		Scanner scanner = new Scanner(System.in);

		//����́A2 �́u-�v�������܂� 12 �����̒����̕������ 1 �擾���邽�߂̂��̂ł��B
		String regexValue = "^(?:\\d{10}|\\d{3}-\\d{4}-\\d{4}|\\d{3}-\\d{3}-\\d{4}|\\d{2}-\\d{4}-\\d{4}|\\d{4}-\\d{4}-\\d{2}|\\d{4}-\\d{2}-\\d{4})$";

		// �ujava Tel.java 052-937-0201�v�̂悤�ȃR�}���h�̏ꍇ
		if (args.length > 0 && args[0].length() == 12 && args[0].matches(regexValue)) 
		{
			tele_number = args[0];
			arr = splitter(tele_number);

		} 

		// �u12345-85965-56974�v�̂悤�ȊԈ�����d�b�`���̓���
		else 
		{
			System.out.println("���̂悤�ɉ����Ă�������: 000-000-0000");
			tele_number = scanner.nextLine();
			arr = splitter(tele_number);
		}

		// �uif�v�X�e�[�g�����g�͐��������͗p�ł�-�v���O�����̎��s��
		while (!tester) {
			if (arr.length == 3 && tele_number.length() == 12 && tele_number.matches(regexValue)) 
			{
				tester = true;
				scanner.close();
			} 

			// �uelse�v�X�e�[�g�����g�͊Ԉ�������͗p�ł�-�v���O�����̎��s��
			else 
			{
				System.out.println("���̂悤�ɉ����Ă�������: 000-000-0000");
				tele_number = scanner.nextLine();
				arr = splitter(tele_number);
			}
		} 

		// �v���ɏ]���ĕ������ꂽ�d�b�ԍ���\�����܂�
		System.out.println("�s�O�ǔԁF " + arr[0]);
		System.out.println("�s���ǔ�: " + arr[1]);
		System.out.println("�����Ҕԍ��F " + arr[2]);
	}

	/**
	 * (���̃��\�b�h�́A�u-�v�����������Ƃ��Ɂu������v�𕪊����܂�)
	 * @param args (���̃��\�b�h�́A�d�b�ԍ��ł��� 1 �̕�������������܂��B)
	 * @return (���̃��\�b�h�͕�����l��Ԃ��܂�[������ԍ�]�B) 
	 * @exception (���̃��\�b�h�͗�O���X���[���܂���B)
	 **/

	public static String[] splitter(String tel) 
	{
		String[] arr = new String[3];
		String s = "";
		int arrLen = 0;

		// ���𕪊����邽��
		for (int i = 0; i < tel.length(); i++) 
		{
			// �C���f�b�N�X 0 ����u"-" �����̑O�v�܂ł� s �Ɋi�[����܂�
			if (tel.charAt(i) != '-')
			{
				s += tel.charAt(i);
			}

			// �Ō�ɒ��o���ꂽ�s�[�X�������ɕۑ�����܂�
			if (i == tel.length() - 1) 
			{
				arr[arrLen] = s;
				arrLen += 1;
			} 
			
			// �u-�v���擾������ɒl��ۑ����܂�
			else if (tel.charAt(i) == '-') 
			{
				arr[arrLen] = s;
				arrLen += 1;
				s = "";
			}
		}
		return arr;
	}

}
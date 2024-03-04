/**
 * @file 2-1.c
 * @brief	��������͂��A���̐������f�����ǂ����𔻒f����
			�v���O�������쐬����B
 * @author Lee Hyeongbeen
 * @date 2024/2/26
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2					/* ���͉񐔂̐ݒ� */
#define SIZE 4					/* ���͌��̐��� */

/* �֐��錾 */
void toEmpty(char *input);
int onlyNum(char *input);
void inputNumber(char input[]);



/** 
 * �f�������f
 * @param (*num) 	���ʂ��鐔��
 * @return 			�f�����f���ʂ̏o��
*/
void prime(int *num) {
	
	int i;			/* �Y���� */
	int count = 0;	/* �f�����f�̃J�E���g */
	
	for (i = 2 ; i < *num ; i++) {		/* 2����num-1�̐����܂� 		*/
		if (*num % i == 0) {			/* num �� i �̐����Ɋ���ė]�肪0�Ȃ�A�f���łȂ��Ɣ��f 	*/
			printf("\n�y���ʁz%d�͑f���ł͂���܂���B", *num);
			count++;
			break;
		}
	}
	if (!count) {						/* �f���ł͂Ȃ��Ɣ��ʂ����������1�x���������������ƂȂ���΁A�f�����Ɣ��f */
		printf("\n�y���ʁz%d�͑f���ł��B", *num);		
	}
}
/* ���C�� */
int main(void){	
	
	int intNum;					/* ���͂������ł������ꍇ */	
	char input[SIZE+1]= {0};	/* ���͒l�̕ۊǔz��		  */
	
	
	printf("�y�J�n�z\n");	
	
	inputNumber(input);			/* ���͊J�n 		*/
	
	intNum = atoi(input);		/* �����Ƃ��ď��� 	*/
	
	printf("\n���͂������� : %d\n",  intNum);
	
	prime(&intNum);				/* �f���m�F			*/
	
	
	printf("\n\n�y�I���z\n");
	
	getchar();
	return 0;
}


/** 
 * �����̓���
 * @param (input) 	�ۊǔz��
 * @return 			�����̓���
*/
void inputNumber(char input[]) {


	int i = 0;				/* �Y���� 				  */

	int numCheck;			/* �����łȂ������̔��ʁA���w�L���̃`�F�b�N */	
	double doubleNum;
	
	printf("\n���l����͂��Ă�������\n>>");
	
	while (1) {								/* ���͂����������܂� */
		
		input[i] = getchar();
		
		if (input[i] == '\n') {				/* ������̔��ɒ�������		  */
			if (i == 0) {					/* �����͂̏ꍇ�A�ē��͂ɖ߂� */
				printf("\n�G���[����\n�����͂ł��B���͂������Ă�������\n>>");
				input[i] = '\0';			/* �Ō���͒l�𐔎��ɕϊ�	  */
				continue;
			}
			break;							/* while�����瓦��A���͂��I���� */
	   	} else {
			if (i >= SIZE)  {				/* ���͌��̐����𒴂����ꍇ�A�ē��͂ɖ߂� */
				printf("\n�G���[����\n���p %d���Ő������Ă܂��B���͂������Ă�������\n>>",SIZE);
				toEmpty(input);
				while (getchar() != '\n');
				i = 0;
				continue;
			} else {
				
				numCheck = onlyNum(input);				/* ���������𔻒f����֐� 			*/
				
				if (numCheck) {							/* �����ł͂Ȃ�������A�ē��͂ɖ߂� */
					printf("\n�G���[����\n���p�̐����̂ݓ��͂ł��܂��B���͂������Ă�������\n>>");
					toEmpty(input);
					while (getchar() != '\n');
					i = 0;
					continue;
				} else{
					i++;								/* ���͐���̏ꍇ�A���̌����͂ɐi��	*/
				}
			}
		}
	}
	


}

/** 
 * �p�����ꂽ���͕����폜����֐�
 *�i�I�[�o�[�t���[�h�~�j
 * @param (*input) ���͕���ۊǂ��Ă���z��
 * @return (*input)�z��̏�����
*/
void toEmpty(char *input){
	
	int j = 0;		/*�Y����*/
	while (input[j] != '\0') {
	  input[j] = '\0';
	  j++;
	}
}

/** 
 * �����݂̂��𔻒f����֐�
 * @param (*input) 	���͕���ۊǂ��Ă���z��
 * @return 1 : true�A��������Ȃ�������Ŕ��f�A�������ɂ�����
 * @return 0 : false�A�����݂̂̕�����Ŕ��f�A�������ɂ����炸
*/
int onlyNum(char *input){
	
	while (*input !=  '\0') {
		if ( !isdigit(*input) ) {
			return 1;
		}
		input++;
	}
	return 0;
}

/**
 * @file 1.c
 * @brief	strcmp�����O�ō��Ȃ����B�֐����́umystrcmp�v�Ƃ���B
 *			�������A���[�v�\���g�p�֎~�B
 * @author Lee Hyeongbeen
 * @date 2024/2/22
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2			/* ���͉񐔂̐ݒ� */
#define SIZE 5			/* ���͌����̐ݒ� */

/* �֐��錾 */
void toEmpty(char *input);
void inputNumber(char inputs[][SIZE] );


/** 
 * ������̔�r�i��strcmp�j
 * @param (*s1) 	1�Ԗڕ�����̕���
 * @param (*s2) 	2�Ԗڕ�����̕���
 * @return 			strcmp�̌���
*/
void mystrcmp(const char *s1, const char *s2) {
	
	
	if (*s1 == '\0' && *s2 == '\0') {				/* 2�̕����񂪈�v�����܂܁A����'\0'�ɂ��ǂ蒅������ */
		printf("��̕����񂪈�v���Ă��܂��B");
		
	} else if (*s1 < *s2) {							/* 1�Ԗڕ�����̕��� < 2�Ԗڕ�����̕��� �̏ꍇ*/
		printf("2�Ԗڂ̕����񂪂����Ƒ傫���ł��B");
		
	} else if (*s1 > *s2) {							/* 1�Ԗڕ�����̕��� > 2�Ԗڕ�����̕��� �̏ꍇ*/
		printf("1�Ԗڂ̕����񂪂����Ƒ傫���ł��B");
	
	} else {										/* �e������̎��̌��ɂ��镶�����p�����[�^�Ƃ��āA�ċA�֐����s�� */
		mystrcmp(s1+1,s2+1);
	}
 
}


/* ���C�� */
int main(void){	
	
	
	char inputs[TIMES+1][SIZE]= {0};		/* �ۊǔz�� */
	
	inputNumber(inputs);					/* ���͊J�n�y��outputNumber���{ */
	
	
	printf("\n�y���ʁz\n");
	
	mystrcmp(inputs[0],inputs[1]);			/* ������̔�r */
	
	printf("\n\n�y�I���z\n");

	getchar();
	return 0;
}


/** 
 * �����̓���
 * @param (*inputs) �ۊǔz��
 * @return 			�����̓���
*/
void inputNumber(char inputs[][SIZE] ){
	
	int i=0;				/* ���͌��̓Y���� 				*/
	int j=0;				/* ���͉񐔂̓Y����  			*/
	
	while (j<TIMES ) {		/* ���͉񐔐ݒ�̓�  */
		
		printf("\n\n%d�Ԗڂ̕��������͂��Ă�������\n>>",j+1);
		
		
		while (1) {						/* ���͂����������܂� */
			inputs[j][i] = getchar();
			
			if (inputs[j][i] == '\n') {			/* ������̔��ɒ������� */
				if (i == 0) {					/* �����͂̏ꍇ�A�ē��͂ɖ߂� */
					printf("\n�G���[����\n�����͂ł��B���͂������Ă�������\n>>");
					inputs[j][i] = '\0';		/* �Ō���͒l�𐔎��ɕϊ� */
					continue;
				} else{
					printf("%d�Ԗڂ̕����� : %s\n", j+1, inputs[j]);	/* ����̏ꍇ�A���̓��͂ɐi�� */
					j++;
					i = 0;
					break;
				}
		   	} else if (i > SIZE-1 ) {			/* ���͔͈͂���O�ꂽ���A�ē��͂����߂� */
				printf("\n�G���[����\n���p��%d���ȓ��œ��͂������Ă�������\n>>", SIZE);
				toEmpty(inputs[j]);
				while (getchar() != '\n');
				i = 0;
				continue;
			} else {
				i++;
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


/**
 * @file 2-2c
 * @brief	������2���͂��A���̊Ԃɂ���f�������ׂĕ\������
			�v���O�������쐬����B�y�P�z�̃v���O�������֐������g�p���邱�ƁB
 * @author Lee Hyeongbeen
 * @date 2024/2/27
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2			/* ���͉񐔂̐ݒ� */
#define SIZE 4			/* ���͌��̐��� */

/* �֐��錾 */
void toEmpty(char *input);
int onlyNum(char *input);
void inputNumber(char inputs[][SIZE], int nums[TIMES] );


/** 
 * �f�������f
 * @param (num) 	���������̕ۊǗ�
 * @return 			�f�����f���ʂ̏o��
*/
void prime(int nums[TIMES]) {
	
	int i;					/* �Y���� */
	int count = 0;			/* �f�����f�̃J�E���g 	*/
	int num1 = nums[0];		/* 1�Ԗړ��͂̐��� 		*/
	int num2 = nums[1];		/* 2�Ԗړ��͂̐��� 		*/
	
	if (num1 > num2) {		/* ���̃R�[�f�B���O���߁A�K��num2��num1���傫��������ׂ�	*/
		i = num1;
		num1 = num2;
		num2 = i;
	}
	
	printf("------------------�Ԃ̑f���o��-------------------\n\n");
	
	for ( ++num1 ; num1 < num2 ; num1++) {			/* num1��num2�̊Ԃ܂ŁAnum1+1����J�n 		*/
		for (i = 2 ; i < num1 ; i++) {				/* i��2����num1�O�܂ŐL�΂�������			*/
			if (num1 % i == 0) {					/* num1���f���ł������ꍇ�Ai�Ƃ̔�r�I��	*/
				break;
			}
		}
		if (num1 == i) {							/* �f���������f����Afor�����瓦�ꂽ�ꍇ	*/
			printf ("%4d ",num1);					/* �o��					*/
			if (++count % 10 == 0) {				/* �o��10�P�ʂŉ��s	*/
				printf("\n");
			}
		}
	}
	
	if (count) {									/* �f�����������ꍇ�A�����o�� */
		printf("\n\n\n�y���ʁz%d�̑f��������܂��B", count);	
			
	} else {										/* 2�̐����̊Ԃɑf�����Ȃ������ꍇ */
		printf("\n\n\n�y���ʁz�f��������܂���ł����B");		
	}
	printf("\n-------------------------------------------------\n");
	
}


/* ���C�� */
int main(void){	
	
	
	char inputs[TIMES+1][SIZE]= {0};		/* �ۊǔz�� */
	int nums[TIMES] = {0};
	
	inputNumber(inputs, nums);				/* ���͊J�n�y��outputNumber���{ */
	
	prime(nums);							/* �f���m�F			*/
	
	
	printf("\n\n�y�I���z\n");
	
	getchar();
	return 0;
}


/** 
 * �����̓���
 * @param (*inputs) �ۊǔz��
 * @param (*nums)   ���͕�����𐔎��������ۊǔz��
 * @return 			�����̓��́A�����ŕۊ�
*/
void inputNumber(char inputs[][SIZE], int nums[TIMES]){
	
	int i = 0;				/* ���͌��̓Y���� 				*/
	int j = 0;				/* ���͉񐔂̓Y����  			*/
	int numCheck;
	
	while (j < TIMES) {		/* ���͉񐔐ݒ�̓�  */
		
		printf("\n\n%d�Ԗڂ̕�������͂��Ă�������\n>>",j+1);
		
		
		while (1) {						/* ���͂����������܂� */
			inputs[j][i] = getchar();
			
			if (inputs[j][i] == '\n') {			/* ������̔��ɒ������� */
				if (i == 0) {					/* �����͂̏ꍇ�A�ē��͂ɖ߂� */
					printf("\n�G���[����\n�����͂ł��B���͂������Ă�������\n>>");
					inputs[j][i] = '\0';		/* �Ō���͒l�𐔎��ɕϊ� */
					continue;
				} else{
					nums[j] = atoi(inputs[j]);
					printf("\n%d�Ԗڂ̐��� : %s\n", j+1, inputs[j]);
					j++;
					i = 0;
					break;
				}
		   	} else if (i > SIZE-1 ) {			/* ���͔͈͂���O�ꂽ���A�ē��͂����߂� */
				printf("\n�G���[����\n���p ������ %d�ȓ��̐����œ��͂������Ă�������\n>>", SIZE);
				toEmpty(inputs[j]);
				while (getchar() != '\n');
				i = 0;
				continue;
			} else {
				
				numCheck = onlyNum(inputs[j]);	/* ���������𔻒f����֐� 			*/
				
				if (numCheck) {					/* �����ł͂Ȃ�������A�ē��͂ɖ߂� */
					printf("\n�G���[����\n���p�̐����̂ݓ��͂ł��܂��B���͂������Ă�������\n>>");
					toEmpty(inputs[j]);
					while (getchar() != '\n');
					i = 0;
					continue;
				} else {
					i++;						/* ���͐���̏ꍇ�A���̌����͂ɐi��	*/
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
 * @param (*input) ���͕���ۊǂ��Ă���z��
 * @return 1 : true�A��������Ȃ�������Ŕ��f�A�������ɂ�����
 * @return 0 : false�A�����݂̂̕�����Ŕ��f�A�������ɂ����炸
*/
int onlyNum(char *input){


	while (*input !=  '\0'){
		if( !isdigit(*input) ){
			return 1;
		}
		input++;
	}
	return 0;
}

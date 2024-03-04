/**
 * @file 2-3.c
 * @brief	�P�`�P�O�O�O�̊Ԃɂ���f�������ׂĕ\������v���O�������쐬����B
			�P�A�Q�ɂƂ��ꂸ�����A���S���Y�����쐬����B
 * @author Lee Hyeongbeen
 * @date 2024/2/27
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2			/* ���͉񐔂̐ݒ� 	*/
#define SIZE 4			/* ���͌��̐���		*/
#define NUM1 1			/* �y�P�`�P�O�O�O�̊ԁz��1 		*/
#define NUM2 1000		/* �y�P�`�P�O�O�O�̊ԁz��1000 	*/



/* �֐��錾 */
void prime(void) {
	
	int i;					/* �Y���� */
	int count = 0;			/* �f�����f�̃J�E���g 	*/
	int num1 = NUM1;		/* 1�̐����̏����� 		*/
	int num2 = NUM2;		/* 1000�̐����̏����� 	*/
	
	if (num1 > num2) {		/* ���̃R�[�f�B���O���߁A�K��num2��num1���傫��������ׂ�	*/
		i = num1;
		num1 = num2;
		num2 = i;
	}
	printf("\n-----------%d��%d�̊Ԃ̑f���o��----------------\n\n",NUM1,NUM2);
	
	for ( ; num1 < num2 ; num1++) {			/* num1��num2�̊Ԃ܂ŁAnum1+1����J�n 		*/
		for (i = 2 ; i < num1 ; i++) {		/* i��2����num1�O�܂ŐL�΂�������			*/
			if (num1 % i == 0) {			/* num1���f���ł������ꍇ�Ai�Ƃ̔�r�I��	*/
				break;
			}
		}
		if (num1 == i) {					/* �f���������f����Afor�����瓦�ꂽ�ꍇ	*/
			printf ("%d\t",num1);			/* �o��					*/
			if (++count % 6 == 0) {			/* �o��6�P�ʂŉ��s	*/
				printf("\n");
			}
		}
	}

	if (count) {							/* �f�����������ꍇ�A�����o�� */
		printf("\n\n�y���ʁz%d�̑f��������܂��B", count);	
			
	} else {								/* 2�̐����̊Ԃɑf�����Ȃ������ꍇ */
		printf("\n\n�y���ʁz�f��������܂���ł����B");		
	}
	printf("\n-------------------------------------------------\n");		
}


/* ���C�� */
int main(void){	
	
	
	prime();		/* �f���m�F			*/
	
	
	printf("\n\n�y�I���z\n");
	
	getchar();
	return 0;
}

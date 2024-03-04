/**
 * @file 3-2.c
 * @brief	�T���T�̃t�B�[���h��p���āA�p�Y�������
 * 			system�����g�p���A�X�N���[�����Ȃ��悤�ɐ��䂹��B
 * @author Lee Hyeongbeen
 * @date 2024/2/28
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>
#include <windows.h>

#define TIMES 1			/* ���͉񐔂̐ݒ� */
#define SIZE 2			/* ���͌����̐ݒ� */
#define END "zz"		/* �I�������̐ݒ� */
#define WIDTH  5		/* ���i2�`11�܂Őݒ�\�j*/
#define LENGTH 7		/* �c�i2�`11�܂Őݒ�\�j*/

/* �֐��錾 */
void toEmpty(char *input);
void inputNumber(char *inputs, char *width, char *length );
int checkInput(char *inputs, char *width, char *length);
void puzzle(char *inputs, int *end, char **puzzles, char *width, char*length);
void showPuzzle(char *width, char *length, char **puzzles);
void makePuzzle(char *width, char *length, char **puzzles);
int chkover(int *j, int *i);



/* ���C�� */
int main(void){	

	int i ;							/* �Y���� 		*/
	int end = 0;					/* �I���̓Y���� */
	char inputs[SIZE] = {0};		/* �ۊǔz�� 	*/
	char width[WIDTH+1] = {0};		/* �t�B�[���h�̉��̔z�� 	*/
	char length[LENGTH+1] = {0};	/* �t�B�[���h�̏c�̔z�� 	*/
	
	char** puzzles = (char**)malloc(sizeof(char*) * LENGTH);	/* �p�Y���̃{�[�h */
	
	
	makePuzzle(width, length, &(*puzzles));						/* �p�Y���̍쐬 */
	
	while (end <= 0) {											/* zz���͂܂ŌJ��Ԃ�	*/
		showPuzzle(width, length, &(*puzzles) );				/* �p�Y���̏o�� */
		inputNumber(inputs, width, length);						/* ����			*/
		puzzle(inputs, &end, &(*puzzles), width, length);		/* �p�Y���ɔ��f */
		system("cls");
	}
	
	printf("\n\n�y�I���z\n");
	
	for ( i = 0 ; i < LENGTH ; i++) {		/* �������[��� */
		free(puzzles[i]);
	}
	free(puzzles);							/* �������[��� */
	
	getchar();
	return 0;
}

/** 
 * ���E�c�E�p�Y���̏�����
 * @param (*width) 		���̔z��
 * @param (*length) 	�c�̔z��
 * @param (*puzzles)	�p�Y��
 * @return 				���E�c�E�p�Y���̏�����
*/
void makePuzzle(char *width, char *length, char **puzzles) {
	
	int i ;
	int j ;
	
	
	for ( i = 0 ; i < WIDTH ; i++ ) {	/* 97 = a�AWIDTH�̐����܂ŃA���t�@�x�b�g���œ���	*/
		width[i] = 97+i;
	} 
	for ( i = 0 ; i < LENGTH ; i++ ) {  /* 65 = A�ALENGTH�̐����܂ŃA���t�@�x�b�g���œ���	*/
		length[i] = 65+i;
	}
	width[WIDTH] = length[LENGTH] = 'z';/*	�ǂ����'z'�̒l��z����Ɏ��i�I���̔��ʁj*/
	
	
	for ( i = 0 ; i < LENGTH ; i++) {					/* �c�̐��̕� 			*/
		*(puzzles+i) = (char*)malloc(WIDTH *sizeof(char) );	/* puzzle[i]�̐錾		*/
		for ( j = 0 ; j < WIDTH ; j++) {				/* ���̐��̕� 			*/
			puzzles[i][j] = 'O';						/* puzzle[i][j]��'O'�ŏ����� 		*/
			/* memset(*(puzzles+i)+j, 'O', sizeof(char*)); */
		}
	}
}

/** 
 * �p�Y���̏o��
 * @param (*width) 		���̔z��
 * @param (*length) 	�c�̔z��
 * @param (*puzzles)	�p�Y��
 * @return 				�p�Y���̏o��
*/
void showPuzzle(char *width, char *length, char **puzzles) {
	
	int i;
	int j;
	
	
	printf(" ");
	for (i = 0; i < WIDTH ; i++) {		/* �ŏ��̉��̐��\���ia,b,c,d,e,****�j	 */
		printf(" %c", width[i]);	
	}
	printf("\n");
	for (i = 0; i < LENGTH ; i++) {		/* �c�ŁA��ԍ��ɂ̏c�̐��\���iA,B,C,D,E,****�j */
		printf("%c ",length[i]);
		
		for (j = 0; j < WIDTH ; j++) {	/* ����c�ipuzzles[i]�j�ɂ��鉡�̏�񂷂ׂďo�� */
		    printf("%c ", puzzles[i][j]); 
		}
		printf("\n");
	}
}

/** 
 * �p�Y���ύX�̊֐�
 * @param (*inputs)		���͕�����
 * @param (*end)		�I�����ʂ̒l
 * @param (*puzzles)	�p�Y�� 
 * @param (*width) 		���̔z��
 * @param (*length) 	�c�̔z��
 * @return 				�p�Y���ύX�̎��s
*/
void puzzle(char *inputs, int *end, char **puzzles, char *width, char *length ) {
	
	int x;	/* �I���������̈ʒu */
	int y;	/* �I�������c�̈ʒu */
	int i;
	int j;

	
	if ( !strcmp(inputs, END) ){			/* ���͂�'zz'�������ꍇ�A�v���O�����I�� */
		(*end)++;
		
	} else {
		
		for ( i = 0 ; i < WIDTH ; i++ ) {	/* �I���������̈ʒu������ */
			if (width[i] == inputs[0]) {
				x = i;
				break;
			}
		}
		for ( i = 0 ; i < LENGTH ; i++ ) {	/* �I�������c�̈ʒu������ */
			if (length[i] == inputs[1]) {
				y = i;
				break;
			}
		}
		
		for ( j = y-1 ; j <= y+1 ; j++) {				/* ���̈ʒu�̏㉺�̃G���A�܂� */
			for ( i = x-1 ; i <= x+1 ; i++) {			/* �c�̈ʒu�̉E���̃G���A�܂� */
				if ( i == x || j == y ) {				/* '�\'�̌`�ŕύX�����邽�߁i�Ȃ���΁��͈͂ŕϊ������j */
					if (chkover(&j, &i) == 1) {			/* ���Əc�̐������p�Y�����̈ʒu�ł��邩���f */
						if (*(*(puzzles+j)+i) == 'O') {	/* ����'O'�Ȃ�'X'�ɕς��� */
							*(*(puzzles+j)+i) = 'X';
						} else {
							*(*(puzzles+j)+i) = 'O';	/* ����'X'�Ȃ�'O'�ɕς��� */
						}
					}
				} else { 
					continue;							/* ���͈͂�'�\'�������������A�ϊ����� */
				}
			}
		}
	}
}


/** 
 * �p�Y�����̈ʒu�ł��邩����
 * @param (*j) 		���̈ʒu
 * @param (*i) 		�c�̈ʒu
 * @return 	1		�p�Y���ύX�̎��s
 * @return 	0		�p�Y���ύX���� 
*/
int chkover(int *j, int *i) {
	if (*i >= 0 && *i < WIDTH && *j >= 0 && *j < LENGTH) return 1;
	
	return 0;
}






/** 
 * ����
 * @param (*inputs)		���͕����� 
 * @param (*width) 		���̔z��
 * @param (*length) 	�c�̔z��
 * @return 				�p�Y���ύX�̎��s
*/
void inputNumber(char *inputs , char *width, char *length ){
	
	int i=0;				/* ���͌��̓Y���� 				*/
	
	printf("\n\n��������͂��Ă�������\n>>");
	
	while (1) {								/* ���͂����������܂� */
		inputs[i] = getchar();
		
		if (inputs[i] == '\n') {			/* ������̔��ɒ������� */
			if (i == 0) {
				system("cls");					/* �����͂̏ꍇ�A�ē��͂ɖ߂� */
				printf("\n�G���[����\n�����͂ł��B���͂������Ă�������\n>>");
				inputs[i] = '\0';			/* �Ō���͒l�𐔎��ɕϊ� */
				continue;
			} else if (i == 1) {
				system("cls");
				printf("\n�G���[����\n�Ԉ�������͂ł��B���͂������Ă�������\n>>");
				toEmpty(inputs);
				i = 0;
				continue;
			} else {
				inputs[i] = '\0';
				printf("���͕��� : %s\n\n", inputs);
				if (checkInput(inputs, width, length)) {	/* ���E�c�̂Ȃ����͂̏ꍇ */
					system("cls");
					printf("\n�G���[����\n�Ԉ�������͂ł��B���͂������Ă�������\n>>");
					toEmpty(inputs);
					i = 0;
					continue;
				} else {					/* ���͂�����̏ꍇ�A���͏I�� */
					break;
				}
			}
	   	} else if (i > SIZE-1  ) {			/* ���͔͈͂���O�ꂽ���A�ē��͂����߂� */
			system("cls");
			printf("\n�G���[����\n���p ������ %d�ȓ��̐����œ��͂������Ă�������\n>>", SIZE);
			toEmpty(inputs);
			while (getchar() != '\n');
			i = 0;
			continue;
		} else {
			i++;
		}
	}
}

/** 
 * 2�����̍ēx�`�F�b�N
 * @param (*inputs)		���͕����� 
 * @param (*width) 		���̔z��
 * @param (*length) 	�c�̔z��
 * @return 1 			����͂ł�����
 * @return 0			�z��ɂ�����͂ł�����
*/
int checkInput(char *inputs, char *width, char *length){

	
	int i ;
	int widthPass = 0;		/* ���̐��J�E���g */
	int lengthPass = 0;		/* �c�̐��J�E���g */
	
	/* ����'z'�ł͂Ȃ������ꍇ */
	if ( (inputs[0]=='z' && inputs[1]!='z') || (inputs[1]=='z' && inputs[0]!='z') ) {
		return 1;
	}
	
	if (strchr(width, inputs[0]) != NULL) {		/* ���̓��͂������������ꍇ */
		widthPass++;
	}
	
	if (strchr(length, inputs[1]) != NULL) {	/* �c�̓��͂������������ꍇ */
		lengthPass++;
	}
	
	if (widthPass != 0 && lengthPass != 0) {	/* ���E�c�̓��͂����Ă͂܂鎞 */
		return 0;
	
	} else return 1;							/* ���͂��ǂ������Ԉ���Ă����� */
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


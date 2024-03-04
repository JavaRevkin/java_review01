/**
 * @file 5.c
 * @brief	���͂��ꂽ�N���ɑΉ����閜�N�J�����_�[��\������v���O�������쐬����B
 * @author Lee Hyeongbeen
 * @date 2024/3/1
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2			/* ���͉񐔂̐ݒ� */
#define YEAR 4			/* �N�̃T�C�Y */
#define MONTH 2			/* ���̃T�C�Y */
#define MONTH_CHECK(arr) (arr>=1 && arr<=12) ? 0 : 1	/* ���̃`�F�b�N�A1�`12�����f */


/* �֐��錾 */
void toEmpty(char *input);
int onlyNum(char *input);
void inputNumber(char inputs[][YEAR], int nums[TIMES] );


int get_day(int yyyy, int mm);
int get_day_of_month(int yyyy, int mm);
int is_leaf_year(int yyyy);
void print_cal(int start_day, int day_num);


/* ���C�� */
int main(void){	
	
	
	char inputs[TIMES+1][YEAR]= {0};		/* ���͒l�̕ۊǔz�� */
	int nums[TIMES] = {0};					/* �N�ƌ��̕ۊǔz�� */
	int start_day;							/* ������1�������j���� */
	int day_num;							/* �����̓����l */
	
	printf("\n\n�y�J�n�z\n");
	
	inputNumber(inputs, nums);						/* �N�ƌ��̓��� */
	
    day_num = get_day_of_month(nums[0], nums[1]);	/* �����̓����l�̏����� */
		
	start_day = get_day(nums[0], nums[1]);			/* ������1�������j���������� */
	
	
	printf("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
	printf("          %d�N%d��         \n", nums[0], nums[1]);
		
    print_cal(start_day, day_num);					/* �J�����_�[�̏o�� */
	
	printf("\n\n�y�I���z\n");
	
	getchar();
	return 0;
}

/** 
 * ���邤�N�̔���
 * @param (*yyyy) 	�N�x
 * @return 1		���邤�N�ł���
 * @return 0		���邤�N�ł͂Ȃ�
*/
int is_leaf_year(int yyyy) {
	
	if ( yyyy % 400 == 0 ) {						/* �N��400�Ŋ��ꂽ��A���邤�N�ł��� */
		return 1;
	}
	if ( ( yyyy % 100 != 0) && ( yyyy % 4 == 0) ) {	/* �N��100�Ŋ��ꂸ�ɂŊ��ꂽ��A���邤�N�ł��� */
		return 1;
	}
	return 0;										/* �łȂ���΂��ׂāA���邤�N�ł͂Ȃ� */
}

/** 
 * �����̓����𔻕�
 * @param (*yyyy) 	�N�x
 * @param (*mm) 	��
 * @return 			�����̓����l
*/
int get_day_of_month(int yyyy,  int mm) {
	
    int day_of_month[13] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	/* ���Ԃ̌����Ƃ̓����l */
	
    day_of_month[2] += is_leaf_year(yyyy);					/* ���邤�N�ł����2����29���ɂ����� */
	
    return day_of_month[mm];								/* �����̓����l��return */
}



/** 
 * yyyy�Nmm����1�������j�������f
 * �i�i�iyyyy-1�j�N�܂ł̓����j+�i�imm-1�j���܂ł̓����j+�P���j���V�Ŋ���A���̐��l�ŗj���𔻒�
 * @param (*yyyy) 	�N�x
 * @param (*mm) 	��
 * @return 			1���̗j�������y���E���E�E�y�z�̏��Ły0�`6�z�̒l��return
*/
int get_day(int yyyy, int mm) {
	
	int past = 0;		/* ���ׂĂ̓����l */
	int y;				/* �N�̓Y���� */
	int m;				/* ���̓Y���� */
	
	for ( y = 1 ; y < yyyy ; y++ ) {			/* �iyyyy-1�j�N�܂ł̓��� */
		past = past + 365 + is_leaf_year(y);
	}
	for ( m = 1 ; m < mm ; m++) {				/* �imm-1�j���܂ł̓��� */
		past = past + get_day_of_month(yyyy, m);
	}
	return (1 + past) % 7;						/*  yyyy�N��(mm)����1�������j����return */
}



/** 
 * �J�����_�[�̏o��
 * @param (*start_day) 	1���̗j���̏��
 * @param (*day_num) 	�����̓���
 * @return 				�J�����_�[�̏o��
*/
void print_cal(int start_day, int day_num)
{
	
	int i;
	int day;	/* �\������������̓Y���� */
	int ke;		/* ����1���̗j���l�̓Y���� */
	
    printf("  ��  ��  ��  ��  ��  ��  �y\n");
    for ( i = 0 ; i < start_day ; i++ ) {		/* 1���̗j���O�܂ł́y�󔒁z�ŃJ�����_�[�\�� */
        printf("    ");
    }
    for ( day = 1, ke = start_day ; day <= day_num ; day++, ke++) {	/* �J�����_�[��day�l�ɂē��l��\��   */
        printf("%4d", day);											/* 1���̗j���l���ꏏ�ɑ�������		 */
        if ( ke % 7 == 6 ) {										/* �J�����_�[�̉��s���s��			 */
            printf("\n");
        }
    }
	printf("\n\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
}





/** 
 * �����̓���
 * @param (*inputs) �ۊǔz��
 * @return 			�����̓��͌�AoutputNumber���s
*/
void inputNumber(char inputs[][YEAR], int nums[TIMES]){
	
	int i=0;				/* ���͌��̓Y���� 				*/
	int j=0;				/* ���͉񐔂̓Y����  			*/
	int numCheck;
	
	while (j<TIMES ) {		/* ���͉񐔐ݒ�̓�  */
		
		switch (j) {
			case 0 : printf("\n\n�N����͂��Ă�������\n>>"); break;
			case 1 : printf("\n\n������͂��Ă�������\n>>"); break;
			default: break;
		}
		
		
		while (1) {						/* ���͂����������܂� */
			inputs[j][i] = getchar();
			
			if (inputs[j][i] == '\n') {			/* ������̔��ɒ������� */
				if (i == 0) {					/* �����͂̏ꍇ�A�ē��͂ɖ߂� */
					printf("\n�G���[����\n�����͂ł��B���͂������Ă�������\n>>");
					inputs[j][i] = '\0';		/* �Ō���͒l�𐔎��ɕϊ� */
					continue;
				} else{
					nums[j] = atoi(inputs[j]);
					if ( j == 1 ) {				/* ���̓��͐��l���P�`�P�Q�ł͂Ȃ��ꍇ�A�ē��͂����߂� */
						if ( MONTH_CHECK(nums[j]) ) {
							printf("\n�G���[����\n���p ������ 1~12�ȓ��œ��͂������Ă�������\n>>");
							toEmpty(inputs[j]);
							i = 0;
							continue;
						}
					}
					j++;
					i = 0;
					break;
				}
		   	} else if ( i > YEAR-1 &&  j == 0 ) {		/* �N�̌��͈͂���O�ꂽ���A�ē��͂����߂� */
				printf("\n�G���[����\n���p ������ %d�ȓ��̐����œ��͂������Ă�������\n>>", YEAR);
				toEmpty(inputs[j]);
				while (getchar() != '\n');
				i = 0;
				continue;
			} else if ( i > MONTH-1 && j == 1 ) {		/* ���̌��͈͂���O�ꂽ���A�ē��͂����߂� */
				printf("\n�G���[����\n���p ������ %d�ȓ��̐����œ��͂������Ă�������\n>>", MONTH);
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
				} else{									/* ���͐���̏ꍇ 						*/
					i++;								/* ���̌����͂ɐi��						*/
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
 * �X�y�[�X & '.' & '-' �敪�\
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

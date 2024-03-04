/**
 * @file 4.c
 * @brief	�Q�O�O�X�N�x�ƂQ�O�P�O�N�x�̔�r���s�������ʂ��o�͂���v���O�������쐬����B
 * @author Lee Hyeongbeen
 * @date 2024/2/29
*/


#include <stdio.h>
#include <string.h>
#include <locale.h>
#include <wchar.h>
#include <stdlib.h>
#include <wctype.h>
#include <ctype.h>

#define NAME_SIZE 10		/* ���O�̒������� */
#define SIZE2009 6			/* 2009�N�x�̔��㐔 */
#define SIZE2010 5			/* 2010�N�x�̔��㐔 */

/* �֐��錾 */
int nameAsc(const void* first, const void* second);


/* ����̌� */
typedef struct Member {
	
	char name[NAME_SIZE];
	int score;
	
} member;

/* ��r���ʂ̌� */
typedef struct Merge {
	
	char name[NAME_SIZE];
	char score[NAME_SIZE];
	
} merge;


/* ���C�� */
void main (){
	
	int i = 0;
	int j = 0;
	int noName = 0;		/* ���O���Ȃ������ꍇ�̓Y���� */
	int mergeLen = 0;	/* merge�̎��۔z�� */
	
	merge merges[SIZE2009 + SIZE2010];
	
	member mem2009[SIZE2009] = { {"����", 325}, {"�ϼ�", 277}, {"�Ŷ", 333}, {"ּ��", 192}, {"����", 412}, {"�ĳ", 270}};
	member mem2010[SIZE2010] = { {"��޷", 210}, {"�ϼ�", 233}, {"����", 445}, {"ּ��", 145}, {"����", 357} };
	
	
	/* if (mem2009[i].name[0] == '\0') { 
        continue; 
    }*/		
	
	
	while (i < SIZE2009) {								/* 2009�z��̂��镪�A1�d���[�v */
	    if ( strcmp( mem2009[i].name, mem2010[j].name) == 0) {		/* ���O����v�����ꍇ */
	        strcpy( merges[mergeLen].name, mem2009[i].name);		/* ���O��merges�ɕۑ� */
	        sprintf (merges[mergeLen].score, "%d", mem2010[j].score - mem2009[i].score);	/* �����merges�ɕۑ� */
	        mergeLen++;												/* mergeLen���� 		*/
	        j = noName = 0;											/* j��noName�̏�����	*/
			i++;													/* 2009�̎��ɐi�� */
	    
		} else {													/* �s��v�����ꍇ */
	        noName++;												/* noName�̑��� */
			
	        if ( noName == SIZE2010 ) {								/* 2010�ɂȂ����O�������ꍇ */
	            strcpy( merges[mergeLen].name, mem2009[i].name);	/* ���O��merges�ɕۑ�	*/
	            strcpy( merges[mergeLen].score, "�ގ�");			/* �ގЂ�merges�ɕۑ� 	*/
	            mergeLen++;											/* mergeLen���� 		*/
		        j = noName = 0;										/* j��noName�̏�����	*/
				i++;												/* 2009�̎��ɐi��		*/
	        } else {
				j++;												/* 2010�̎��ɐi��		*/
	        }
		}
	}
	
	for (i = 0 ; i < SIZE2010 ; i++) {								/* ���Ђ̔��� */
		if (mem2010[i].name[0] == '\0') { 
	        continue; 
	    }		
	    for (j = 0; j < SIZE2009; j++) {							
			if ( strcmp( mem2010[i].name, mem2009[j].name) != 0) {	/* ���O���s��v�����ꍇ */
				noName++;
	        } 
	    }
		if ( noName == SIZE2009 ) {									/* ���O��2009�ɂ��Ȃ��ꍇ */
			strcpy( merges[mergeLen].name, mem2010[i].name );		/* ���O��merges�ɕۑ�	*/
			strcpy( merges[mergeLen].score, "����" );				/* ���Ђ�merges�ɕۑ� 	*/
			mergeLen++;												/* mergeLen���� 		*/
		}
		noName = 0;													/* ��v�������O���������ꍇ�A���̊m�F�ɐi�� */
	}
	
	qsort( merges , mergeLen, sizeof(merge), nameAsc);				/* ���O�̏����̎��s */
	
	printf("\n\n�y����グ���z�z\n");
	for ( i = 0 ; i < mergeLen ; i++ ) {							/* ���ʏo�� */
		printf("%s\t", merges[i].name);
		printf("%s\n", merges[i].score);
	}
	
	getchar();
	getchar();
	
	
}

/**
 * ���O�̏����֐�(qsort)
 * @return  	�\�[�g�̎��s
*/
int nameAsc(const void* first, const void* second) {
    const merge* a = (const merge*)first;
    const merge* b = (const merge*)second;
    return strcmp(a->name, b->name);
}



/*
	for (i = 0 ; i < SIZE2009 ; i++) {

	    for (j = 0 ; j < SIZE2010 ; j++) {
			if ( strcmp( mem2009[i].name, mem2010[j].name) == 0) {
				strcpy( merges[symbol].name, mem2009[i].name );
				sprintf( merges[symbol].score, "%d", mem2010[j].score - mem2009[i].score );
				symbol++;
				break;
	        } else {
				noName++;
				if ( noName == SIZE2010 ) {
					strcpy( merges[symbol].name, mem2009[i].name );
					strcpy( merges[symbol].score, "����" );
					symbol++;
				}
			}
	    }
		noName = 0;
	}

*/
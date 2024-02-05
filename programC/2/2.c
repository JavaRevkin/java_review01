#include <stdio.h>
#include <string.h>
#include <locale.h>
#include <wchar.h>
#include <stdlib.h>
#include <wctype.h>
#include <ctype.h>

/**
 * @file 2-2.c
 * @brief �u���O�v�u�Z���v�u���N�����v�u�d�b�ԍ��v�̎l�̍��ڂ��������\���̂��`���A
 *  		�P�O�����̃f�[�^����͂��Ă��ꂼ����o�͂���v���O�����B
 * @author Lee Hyeongbeen
 * @date 2024/1/22
*/

#define NAME_SIZE 21		/* ���O�̍ő啶�����i�S�p�Ȃ̂�*2�j*/
#define ADD_SIZE 129		/* �Z���̍ő啶�����i�S�p�Ȃ̂�*2�j*/
#define BIRTH_SIZE 11		/* ���N�����̍ő啶���� */
#define TEL_SIZE 17		/* �d�b�ԍ��̍ő啶���� */
#define TIMES 10			/* ���͌�����*/

/**
 * �\����
 * @param (*name)		���O�̔z��
 * @param (*address) 	�Z���̔z��
 * @param (*birth) 	���N�����̔z��
 * @param (*tel) 		�d�b�ԍ��̔z��
*/
struct Person {
    char name[NAME_SIZE];
    char address[ADD_SIZE];
    char birth[BIRTH_SIZE];
    char tel[TEL_SIZE];
};

/*�֐��錾*/
void inputs(char *ch, char *input,struct Person ps[],int *t);
void toEmpty(char *input);
int fwidthCheck(char *input,int *j);
int numberCheck(char *input,int *j);
int sizeCheck(long *i,int *j);

/*���C��*/
void main() {
    

	char ch='\0';					/*���͕���*/
	char input[ADD_SIZE+1]= {0};			/*���͗�̕ۊǔz��*/
	int t=0;						/*�Y����*/
	struct Person ps[TIMES];			/*����\���̂̔z��*/
	
	setlocale(LC_ALL, "Japanese"); 
	

	while(t<TIMES){
		inputs(&ch,input, &ps[t],&t);
		printf("\n//////�y�y%d�Ԗڂ̐l�̓��͊����z�z///////",t+1);	
		t++;
	}
	t=0;
	
	printf("\n�y%d�����͌��ʁz\n",TIMES);
	
	while(t<TIMES){
		printf("\n�y%d�Ԗ��ڂ̐l�z\n",t+1);
		printf(" ���O�F %s\t|| �Z���F %s\t|| ���N�����F %s\t|| �d�b�ԍ��F %s\n",
     			ps[t].name, ps[t].address, ps[t].birth, ps[t].tel);
		t++;
	}
	printf("\n�y�I���z\n");
	getchar();
}

/**
 * ���͊֐�
 * @param (*ch)	 ���͕���
 * @param (*input) ���͕���ۊǂ��Ă���z��
 * @param (*ps)	 ����\���̂̔z��
 * @param (*t)	 �Y����
 * @return 		 ������̓o�^
*/
void inputs(char *ch, char *input, struct Person ps[],int *t){
	
	long i=0;						/*�Y����*/	
	int j =0;
	printf("\n////////////////////////////////////////");	
	printf("\n///////�y�y%d�Ԗڐl�̏��X�V�z�z////////\n",*t+1);
	
	for( ; j<4 ;j++){
			
		switch(j){
			case 0: printf("\n�y���O��S�p�œ��́z");
					printf("\n��F��؁@�����^���E\n>>"); break;
			case 1: printf("\n�y�Z����S�p�œ��́z");
					printf("\n��F���m�����É��s����㊯���R�T�[�P�U�̂Q�K"); 
					printf("\n�i�����͊�]�̏ꍇ�A�G���^�[�����j\n>>");break;
			case 2: printf("\n�y���N�����𔼊p�œ��́z");
					printf("\n��F1900/07/10\n");
					printf("\n�i�����͊�]�̏ꍇ�A�G���^�[�����j\n>>");break;
			case 3: printf("\n�y�d�b�ԍ��𔼊p�œ��́z"); 
					printf("\n��F080-9987-6461\n");
					printf("\n�i�����͊�]�̏ꍇ�A�G���^�[�����j\n>>");break;
			default: printf("end\n%s\n",input); break;
		}		
		while(1){

			*ch =getchar();
			
			/*�����͂̏ꍇ*/
			if(*ch=='\n'){
				if (i == 0 && j==0) {
		     			printf("\n�����͂ł��B\n���͂������Ă�������\n>> ");
					toEmpty(input);
		     			continue;		
				}
				else {
					switch(j){
						case 0:
							if(fwidthCheck(input,&j)){
								toEmpty(ch);
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("���O���́F%s\n",input);
								strcpy(ps->name ,input);	
								break;
							}
						case 1:
							if(fwidthCheck(input,&j)){
								toEmpty(ch);
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("�Z�����́F%s\n",input);
								strcpy(ps->address ,input);
								break;
							}
						case 2:
							if(numberCheck(input,&j)){
								toEmpty(ch);
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("���N�������́F%s\n",input);
								strcpy(ps->birth ,input);
								break;
							}
						case 3:
							if(numberCheck(input,&j)){
								toEmpty(ch);		
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("�d�b�ԍ����́F%s\n",input);
								strcpy(ps->tel ,input);
								break;
							}
						default: break;
					}
					toEmpty(input);			/*�p�����ꂽ���͕����폜����֐�*/
					i=0;
					break;
				}
		   	}
			else{
				
				if(sizeCheck(&i,&j) ){	
					toEmpty(input);
					while ( (*ch=getchar()) != '\n');
					i=0;	
					continue;
				}
				input[i++] = *ch;
			}
		}
	}
}

/**
 * �S�p�����̌���֐�
 * @param (*input) ���͕���ۊǂ��Ă���z��
 * @param (*j)	 �Y�����i���O�ƏZ���j
 * @return 1 	 �S�p�̕����ł͂Ȃ������ꍇ�A�G���[����
 * @return 2 	 �������͂̏ꍇ�A�G���[����
 * @return 3 	 �L�����͂̏ꍇ�A�G���[����
 * @return 4 	 �X�y�[�X�Ŏn�܂���͂̏ꍇ�A�G���[����
 * @return 0 	 ���͂ɖ��Ȃ��A���ɐi��
*/
int fwidthCheck(char *input,int *j){
	
	
	int l;
	int k = strlen(input);
	wchar_t *wc;
	wchar_t exceptName[] =  L"�[�`�����O�����{�b����";
	wchar_t exceptAddress[] =  L"�`�����O�����{�b����";
	int symbol; 
	int space;
 	int p;
	
	/*�������[���蓖�Ă�*/
	wc = (wchar_t *)malloc((k + 1) * sizeof(wchar_t));
	if (wc == NULL) {
		  printf("error\n");
		  return -1;  
	}
	
	if(k==0){
		return 0;
	}
	
	memset(wc, '\0', k + 1);
	mbstowcs(wc,input,k);
	p=wcslen(wc);
	
	if( !iswspace(input[0]) && (k==1 || k%2==1) ){
		printf("\n���p�͓��͂ł��܂���B\n���͂������Ă�������\n>>");
		free(wc);
		return 1;
	}

	for (l=0 ; l< p ; l++) {
		
		symbol = 0;
		space=0;
		
		if( l==0 && wc[l]==L'�@'){
			space++;
		}
		
		if(*j==0){
		      if (wcschr(exceptName, wc[l]) != NULL) {
		      	symbol++;
			}
		}else{
			if (wcschr(exceptAddress, wc[l]) != NULL) {
		  		symbol++;
			}
		}
		if( iswdigit(wc[l]) && *j==0 ){
			printf("\n�����͓��͂ł��܂���B\n���͂������Ă�������\n>>");
			free(wc);
			return 2;
		}else if( space!=0 || isspace(input[l]) ){
			printf("\n1���ڂ̓X�y�[�X�����͂ł��܂���B\n���͂������Ă�������\n>>");
			free(wc);
			return 4;
		}else if(  iswpunct(wc[l]) || symbol!=0 ){
			
			if( (wc[l]==L'�[' && *j==1) || iswdigit(wc[l-1])){
				continue;
			}else{
				printf("\n�L���͓��͂ł��܂���B\n���͂������Ă�������\n>>");
				free(wc);
				return 3;
			}
		}else if( wc[l] < 0x100  ){
			printf("\n�S�p�̂ݓ��͂ł��܂��B\n���͂������Ă�������\n>>");
			free(wc);
			return 1;
		}
		else {
			continue;
		}	
	}
	wcstombs(input, wc, k);
	free(wc);
	return 0;
}

/**
 * ���p�����̌���֐�
 * @param (*input) ���͕���ۊǂ��Ă���z��
 * @param (*j)	 �Y�����i���N�����Ɠd�b�ԍ��j
 * @return 1 	 ���p��L���̓��͂��Ԉ���Ă���ꍇ�A�G���[����
 * @return 2 	 '/'�̓��͂��Ԉ���Ă���ꍇ�A�G���[����
 * @return 3 	 '-'�̓��͂��Ԉ���Ă���ꍇ�A�G���[����
 * @return 4 	 ���N�����͕K��10���œ��́A�łȂ��ƃG���[����
 * @return 0 	 ���͂ɖ��Ȃ��A���ɐi��
*/
int numberCheck(char *input,int *j){
	
	int slashCount=0;
	int barCount=0;
	int k;
	int len=strlen(input);
	
	if(len==0)return 0;
	
	if(*j==2 && len!=BIRTH_SIZE-1){
		printf("\n%d���̔��p�Ő��������͂��Ă��������B\n>>",BIRTH_SIZE-1);
		return 2;
	}
	else{
		for(k=0; k< len ; k++){
			if( !isdigit(input[k]) ){
				if(*j==2 && input[k] =='/'){
					slashCount++;
					continue;
				}
				else if(*j==3 && input[k]=='-'){
					barCount++;
					continue;
				}
				else{
					printf("\n���p��L���̓��͂��Ԉ���Ă��܂��B\n���͂������Ă�������\n>>");						
					return 1;
				}
			}
		}
		if(*j==2 && (slashCount!=2 || input[4] !='/' || input[7]!='/') ){		/* '/'��2����Ȃ������ꍇ�A���͂������悤�ɂ��邽��*/
			printf("\n'/'�̓��͂��Ԉ���Ă��܂��B\n���͂������Ă�������\n>>");
			return 2;
		}
		else if(*j==3 && (barCount!=2 || (strstr(input, "--") != NULL) || input[0]=='-' || input[len-1]=='-' ) ){	/* '�|'��2����Ȃ������ꍇ�A���͂������悤�ɂ��邽��*/
			printf("\n'-'�̓��͂��Ԉ���Ă��܂��B\n���͂������Ă�������\n>>");
			return 3;
		}
	}
	return 0;
}

/**
 * ������̒��������`�F�b�N�֐�
 * @param (*i) 	�Y����
 * @param (*j)	�Y����
 * @return 1 	�����񐔂𒴂��A�G���[����
 * @return 0 	 ���͂ɖ��Ȃ��A���ɐi�� 
*/
int sizeCheck(long *i,int *j){
	
    int maxSize;	
	switch(*j){
		case 0: maxSize = NAME_SIZE; break;
		case 1: maxSize = ADD_SIZE; break;
		case 2: maxSize = BIRTH_SIZE; break;
		case 3: maxSize = TEL_SIZE; break;
		default: return 0;
	}
	if(*i >maxSize-2 ){
		if(*j==2||*j==3){
			printf("\n������%d���ɐ������Ă܂��B\n���͂������Ă�������\n>>",maxSize-1);	
		}
		else {
			printf("\n������%d���ɐ������Ă܂��B\n���͂������Ă�������\n>>",maxSize/2);
		}		
		return 1;
	}
	return 0;
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
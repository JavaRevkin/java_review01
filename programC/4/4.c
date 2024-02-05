#include <stdio.h>
#include <string.h>
#include <locale.h>
#include <wchar.h>
#include <stdlib.h>
#include <wctype.h>
#include <ctype.h>

/**
 * @file 2-4.c
 * @brief	 2-3����P�����Ƀ������̊m�ۂ��A�f�[�^�̒ǉ��A�폜�A�ύX������B
 * 		�����ă��j���[�ɂđ��삪�ł���v���O����
 * @author Lee Hyeongbeen
 * @date 2024/1/27
*/
#define NAME_SIZE 21		/* ���O�̍ő啶�����i�S�p�Ȃ̂�*2�j*/
#define ADD_SIZE 129		/* �Z���̍ő啶�����i�S�p�Ȃ̂�*2�j*/
#define BIRTH_SIZE 11		/* ���N�����̍ő啶���� */
#define TEL_SIZE 17		/* �d�b�ԍ��̍ő啶���� */
#define SIZE 5				/*���ID���̐���*/

/**
 * �\����
 * @param (*id)		���ID�ԍ�
 * @param (*name)		���O�̔z��
 * @param (*address) 	�Z���̔z��
 * @param (*birth) 	���N�����̔z��
 * @param (*tel) 		�d�b�ԍ��̔z��
 * @param (*pre) 		�O�̉�����
 * @param (*next) 	���̉����� 
*/
typedef struct Member {
	
	int id;
    	char name[NAME_SIZE];
    	char address[ADD_SIZE];
    	char birth[BIRTH_SIZE];
   	 char tel[TEL_SIZE];
	struct Member *pre;
	struct Member *next;

} member;


/*�֐��錾*/
void toEmpty(char *input);
int fwidthCheck(char *input,int *j);
int numberCheck(char *input,int *j);
int sizeCheck(long *i,int *j);
member *inputs(int *t, member **pre_member, member **next_member,int caseNum);
void deleteMember(member **pre_member, member **next_member);
member * updateMember(member **pre_member);
void findAll(member **pre_member);
void findMemberById(member **pre_member);


/*���C��*/
void main() {
    
	int caseNum;
	char choice[SIZE]= {0};
	int t=0;
	int end=0;
	member *pre_member;
	member *next_member;
	member *new_member;
	member *person;
		
	setlocale(LC_ALL, "Japanese");	
	
	pre_member = (member *)malloc(sizeof(member));
	next_member = (member *)malloc(sizeof(member));	
	memset(pre_member, '\0', sizeof(member)+1);
	memset(next_member, '\0',sizeof(member)+1);	

		
	while(1){
		
		printf("\n--------------------MAIN MENU-----------------------\n");
		printf("| 1.�o�^                                           |\n");
		printf("| 2.�폜                                           |\n");
		printf("| 3.�ύX                                           |\n");
		printf("| 4.�ꗗ����                                       |\n");
		printf("| 5.�ꌏ����                                       |\n");
		printf("| 6.�I��                                           |\n");
		printf("----------------------------------------------------\n");
		
		printf("�ԍ�����͂��Ă�������\n>>");
		
		choice[0]=getchar();
		
		if(choice[0]=='\n'){
			printf("\n�����͂ł��B���͂������Ă��������B\n\n");
			continue;
		}
		if(getchar()!='\n'){
			printf("\n���͂��Ԉ���Ă��܂��B\n\n");
			while((getchar())!='\n');
			continue;
		}
		
		switch(choice[0]){
			case '1': new_member = inputs(&t, &pre_member, &next_member, 1);	break;
			case '2': deleteMember(&pre_member, &next_member);					break;
			case '3': new_member = updateMember(&pre_member);					break;
			case '4': findAll(&pre_member);										break;
			case '5': findMemberById(&pre_member);								break;
			case '6': end++;													break;
			default : printf("���͂��Ԉ���Ă��܂�\n\n"); 						break;
		}
		if(end){
			printf("\n�y�v���O�����I���z\n");
			break;
		}	
	}
	free(pre_member);
	free(next_member);
	if(t!=0 ){
		free(new_member);
		free(person);					
	}
	getchar();
}


/**
 * ���͊֐� 
 * @param (*t)	 	 	�Y����
 * @param (*pre_member)		�ŏ�����̏��
 * @param (*next_member) 	���̉���̏��
 * @param (*caseNum)	 	����o�^or����C���̋�؂�
 * @return 		 		������
*/
member *inputs(int *t, member **pre_member, member **next_member,int caseNum){
	

	char ch ;						/*���͕���*/
	long i=0;						/*�Y����*/	
	int j =0;
	char *input = (char *)malloc((ADD_SIZE + 1) * sizeof(char));
	member *new_member =(member *)malloc(sizeof(member));	
	memset(input, '\0', ADD_SIZE + 1);
	memset(new_member, '\0',sizeof(member)+1);	

	
	
	printf("\n----------------------------------------------------\n");
	if(caseNum==1){	
		printf("\n--------------�y�yID�u%d�v�ŉ���o�^�z�z-------------\n",*t+1);
	}else{
		printf("\n--------------�y�yID�u%d�v�̏��X�V�z�z-------------\n",*t);	
	}
	
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

			/*�����͂̏ꍇ*/
			if( (ch =getchar())=='\n' ){
				if (i == 0 && j==0) {
		     			printf("\n�����͂ł��B\n���͂������Ă�������\n>> ");
						toEmpty(input);				
		     			continue;		
				}
				else {
					switch(j){
						case 0:
							if(fwidthCheck(input,&j)){
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("���O���́F%s\n",input);
								strcpy( new_member->name ,input);							
								break;
							}
						case 1:
							if(fwidthCheck(input,&j)){
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("�Z�����́F%s\n",input);
								strcpy(new_member->address ,input);
								break;
							}
						case 2:
							if(numberCheck(input,&j)){
								
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("���N�������́F%s\n",input);
								strcpy(new_member->birth ,input);
								break;
							}
						case 3:
							if(numberCheck(input,&j)){
										
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("�d�b�ԍ����́F%s\n",input);
								strcpy(new_member->tel ,input);
								break;
							}
						default: break;
					}
					toEmpty(input);			/*�p�����ꂽ���͕����폜����֐�*/
					i=0;
					break;
				}
			}else{	
				if(sizeCheck(&i,&j) ){	
					toEmpty(input);
					while ( (ch=getchar()) != '\n');	/*���͂ŗ]�肪����̂ŕK�v*/	
					i=0;	
					continue;
				}
				input[i++] = ch;
			}
		}
	}
	if(caseNum==3){
		new_member->id =*t;			
	}
	else{	
		printf("\n--------------�y�yID�u%d�v�̓o�^�����z�z-------------\n\n",*t+1);	
		new_member->id =*t+1;	

		if (  (*pre_member)->id ==0) {
		    new_member->pre = NULL;
		    new_member->next = NULL;
		    *pre_member = new_member;
		} else if( (*pre_member)->next == NULL ){
		    new_member->pre = *pre_member;
		    new_member->next = NULL;
		    (*pre_member)->next = new_member;
		} else{
			new_member->pre = *next_member;			
			(*next_member)->next = new_member;
			new_member->pre = *next_member;		
		}
		*next_member = new_member;
		(*t)++;
	}
	toEmpty(input);
	free(input);	
	return new_member;
}


/**
 * ����폜�̊֐� 
 * @param (*pre_member)		�ŏ�����̏��
 * @param (*next_member) 	���̉���̏��
 * @return 		 		������폜�̎��s
*/
void deleteMember(member **pre_member, member **next_member){
	
	int idCheck;
	int len;
	char choice[SIZE]= {0};
	member *person;
	
	while(1){
		if((*pre_member)->id==0){
			printf("\n----------------���݉�������܂���------------------\n\n");
			break;
		}
		printf("-----------------ID����͂��Ă�������---------------\n>>");
		len=0;
	
		while((choice[len]=getchar())!='\n'){
			len++;					
		}
		if(len>SIZE){
			printf("���ID��%d���ȓ��ł��B���͂������Ă��������B\n\n",SIZE);
			toEmpty(choice);
			continue;
		}
		
		idCheck=atoi(choice);
		
		person = (*pre_member);
		len=0;
		
		while (person != NULL) {
		        if(idCheck==person->id){
					len++;
					break;
				}else{
					person = person->next;
					continue;
				}
		}
		if(len!=0){
			printf("\n--------------�y�yID�u%d�v�̍폜�����z�z-------------\n\n",person->id);				
			if(person->id== (*pre_member)->id){
				if( (*pre_member)->next ==NULL){							/*�P�݂̂�PRE�폜*/
					memset((*pre_member), '\0', sizeof(member)+1);
				}else{														/*2�ȏ゠����PRE�폜*/
					(*pre_member) = person->next;
				}
			}else if(person->id== (*next_member)->id){						/*�Ō�폜*/
				(*next_member) = person->pre;
				(*next_member)->next =NULL;
			}else{															/*�^�񒆍폜*/
				person->pre->next = person->next;
				person->next->pre = person->pre;	
			}	
		}else{
			printf("\n--------------------���͌���------------------------\n");
			printf("�����T���܂���ł����B\n\n");
			printf("----------------------------------------------------\n");
		}					
		break;
	}
}

/**
 * ����C���̊֐� 
 * @param (*pre_member)		�ŏ�����̏��
 * @return 		 		�C������������
*/
member *updateMember(member **pre_member){
	
	int idCheck;
	int len;
	char choice[SIZE]= {0};
	member *person;
	member *new_member =(member *)malloc(sizeof(member));	
	memset(new_member, '\0',sizeof(member)+1);	
	
	while(1){
		if(  (*pre_member)->id==0){
			printf("\n----------------���݉�������܂���------------------\n\n");
			break;
		}
		printf("-----------------ID����͂��Ă�������---------------\n>>");
		len=0;
	
		while((choice[len]=getchar())!='\n'){
			len++;					
		}
		if(len>SIZE){
				printf("���ID��%d���ȓ��ł��B���͂������Ă��������B\n\n",SIZE);
				toEmpty(choice);
				continue;
			}
		idCheck=atoi(choice);
		person = (*pre_member);
		len=0;

		while (person != NULL) {
	        if(idCheck==person->id){
				len++;
				break;
			}else{
				person = person->next;
				continue;
			}
		}
		if(len!=0){
			new_member = inputs(&(person->id), NULL, NULL,3);
			new_member->pre = person->pre;
			new_member->next = person->next;	
			*person = *new_member;
			printf("\n------------�y�yID�u%d�v�̏C�������z�z---------------\n",person->id);	
				
			return new_member;
		}else{
			printf("\n--------------------���͌���------------------------\n");			
			printf("�����T���܂���ł����B\n\n");
			printf("----------------------------------------------------\n");	
			return person;
		}		
	}
	return 0;
}

/**
 * ���ׂẲ�����̕\���֐� 
 * @param (*pre_member)		�ŏ�����̏��
 * @return 		 		���ׂẲ�����̏o��
*/
void findAll(member **pre_member){
	
	member *person;	
	while(1){
		if(  (*pre_member)->id==0){
			printf("\n----------------���݉�������܂���------------------\n\n");
			break;
		}
		else{
			person = (*pre_member);
			printf("\n--------------------����ꗗ------------------------\n");			
			while (person != NULL) {
			    printf("ID:%d\t", person->id);
			    printf("���O:%s\n", person->name);
			    if (person->next != NULL) {												
			    	person = person->next;
					continue;
			    }else{
					break;
				}
			}
			printf("----------------------------------------------------\n");
			break;
		}
	}
}

/**
 * �w��ID�̉�����̕\���֐� 
 * @param (*pre_member)		�ŏ�����̏��
 * @return 		 		�w��ID�̉�����̏o��
*/
void findMemberById(member **pre_member){
	
	member *person;	
	int idCheck;
	int len;
	char choice[SIZE]= {0};
	
	while(1){	
		if( (*pre_member)->id==0){
			printf("\n----------------���݉�������܂���------------------\n\n");
			break;
		}	
		else{
		printf("-----------------ID����͂��Ă�������---------------\n>>");
			len=0;
			
			while((choice[len]=getchar())!='\n'){
				len++;					
			}
			if(len>SIZE){
					printf("���ID��%d���ȓ��ł��B���͂������Ă��������B\n\n",SIZE);
					toEmpty(choice);
					continue;
			}
			
			idCheck=atoi(choice);
			
			person = (*pre_member);
			len=0;
			printf("\n--------------------������------------------------\n");
			while (person != NULL) {
		        if(idCheck==person->id){
					len++;
					break;
				}else{
					person = person->next;
					continue;
				}
			}
			if(len!=0){
				printf("ID:%d\n", person->id);
				printf("���O:%s\n", person->name);
				printf("�Z��:%s\n", person->address);
				printf("���N����:%s\n", person->birth);						
				printf("�d�b�ԍ�:%s\n", person->tel);							
			}else{
				printf("�����T���܂���ł����B\n\n");
			}		
			
			printf("----------------------------------------------------\n");			
			break;
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
		}else if( space!=0 || iswspace(input[l]) ){
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
					/*free(input);*/
					return 1;
				}
			}
		}
		if(*j==2 && (slashCount!=2 || input[4] !='/' || input[7]!='/') ){		/* '/'��2����Ȃ������ꍇ�A���͂������悤�ɂ��邽��*/
			printf("\n'/'�̓��͂��Ԉ���Ă��܂��B\n���͂������Ă�������\n>>");
			return 2;
		}
		else if(*j==3 && (barCount!=2 || (strstr(input, "--") != NULL) 
				|| input[0]=='-' || input[len-1]=='-' ) ){						/* '�|'��2����Ȃ������ꍇ�A���͂������悤�ɂ��邽��*/
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
		case 1: maxSize = ADD_SIZE;  break;
		case 2: maxSize = BIRTH_SIZE;break;
		case 3: maxSize = TEL_SIZE;  break;
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
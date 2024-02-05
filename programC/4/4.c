#include <stdio.h>
#include <string.h>
#include <locale.h>
#include <wchar.h>
#include <stdlib.h>
#include <wctype.h>
#include <ctype.h>

/**
 * @file 2-4.c
 * @brief	 2-3から１件毎にメモリの確保し、データの追加、削除、変更をする。
 * 		そしてメニューにて操作ができるプログラム
 * @author Lee Hyeongbeen
 * @date 2024/1/27
*/
#define NAME_SIZE 21		/* 名前の最大文字数（全角なので*2）*/
#define ADD_SIZE 129		/* 住所の最大文字数（全角なので*2）*/
#define BIRTH_SIZE 11		/* 生年月日の最大文字数 */
#define TEL_SIZE 17		/* 電話番号の最大文字数 */
#define SIZE 5				/*会員ID桁の制限*/

/**
 * 構造体
 * @param (*id)		会員ID番号
 * @param (*name)		名前の配列
 * @param (*address) 	住所の配列
 * @param (*birth) 	生年月日の配列
 * @param (*tel) 		電話番号の配列
 * @param (*pre) 		前の会員情報
 * @param (*next) 	次の会員情報 
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


/*関数宣言*/
void toEmpty(char *input);
int fwidthCheck(char *input,int *j);
int numberCheck(char *input,int *j);
int sizeCheck(long *i,int *j);
member *inputs(int *t, member **pre_member, member **next_member,int caseNum);
void deleteMember(member **pre_member, member **next_member);
member * updateMember(member **pre_member);
void findAll(member **pre_member);
void findMemberById(member **pre_member);


/*メイン*/
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
		printf("| 1.登録                                           |\n");
		printf("| 2.削除                                           |\n");
		printf("| 3.変更                                           |\n");
		printf("| 4.一覧検索                                       |\n");
		printf("| 5.一件検索                                       |\n");
		printf("| 6.終了                                           |\n");
		printf("----------------------------------------------------\n");
		
		printf("番号を入力してください\n>>");
		
		choice[0]=getchar();
		
		if(choice[0]=='\n'){
			printf("\n未入力です。入力し直してください。\n\n");
			continue;
		}
		if(getchar()!='\n'){
			printf("\n入力が間違っています。\n\n");
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
			default : printf("入力が間違っています\n\n"); 						break;
		}
		if(end){
			printf("\n【プログラム終了】\n");
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
 * 入力関数 
 * @param (*t)	 	 	添え数
 * @param (*pre_member)		最初会員の情報
 * @param (*next_member) 	次の会員の情報
 * @param (*caseNum)	 	会員登録or会員修正の区切り
 * @return 		 		会員情報
*/
member *inputs(int *t, member **pre_member, member **next_member,int caseNum){
	

	char ch ;						/*入力文字*/
	long i=0;						/*添え数*/	
	int j =0;
	char *input = (char *)malloc((ADD_SIZE + 1) * sizeof(char));
	member *new_member =(member *)malloc(sizeof(member));	
	memset(input, '\0', ADD_SIZE + 1);
	memset(new_member, '\0',sizeof(member)+1);	

	
	
	printf("\n----------------------------------------------------\n");
	if(caseNum==1){	
		printf("\n--------------【【ID「%d」で会員登録】】-------------\n",*t+1);
	}else{
		printf("\n--------------【【ID「%d」の情報更新】】-------------\n",*t);	
	}
	
	for( ; j<4 ;j++){
		
			
		switch(j){
			case 0: printf("\n【名前を全角で入力】");
					printf("\n例：鈴木　ももタロウ\n>>"); break;
			case 1: printf("\n【住所を全角で入力】");
					printf("\n例：愛知県名古屋市東区代官町３５ー１６の２階"); 
					printf("\n（未入力希望の場合、エンター押し）\n>>");break;
			case 2: printf("\n【生年月日を半角で入力】");
					printf("\n例：1900/07/10\n");
					printf("\n（未入力希望の場合、エンター押し）\n>>");break;
			case 3: printf("\n【電話番号を半角で入力】"); 
					printf("\n例：080-9987-6461\n");
					printf("\n（未入力希望の場合、エンター押し）\n>>");break;
			default: printf("end\n%s\n",input); break;
		}		
		while(1){

			/*未入力の場合*/
			if( (ch =getchar())=='\n' ){
				if (i == 0 && j==0) {
		     			printf("\n未入力です。\n入力し直してください\n>> ");
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
								printf("名前入力：%s\n",input);
								strcpy( new_member->name ,input);							
								break;
							}
						case 1:
							if(fwidthCheck(input,&j)){
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("住所入力：%s\n",input);
								strcpy(new_member->address ,input);
								break;
							}
						case 2:
							if(numberCheck(input,&j)){
								
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("生年月日入力：%s\n",input);
								strcpy(new_member->birth ,input);
								break;
							}
						case 3:
							if(numberCheck(input,&j)){
										
								i=0;
								toEmpty(input);
								continue;
							}else {
								printf("電話番号入力：%s\n",input);
								strcpy(new_member->tel ,input);
								break;
							}
						default: break;
					}
					toEmpty(input);			/*却下された入力分を削除する関数*/
					i=0;
					break;
				}
			}else{	
				if(sizeCheck(&i,&j) ){	
					toEmpty(input);
					while ( (ch=getchar()) != '\n');	/*入力で余りがあるので必要*/	
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
		printf("\n--------------【【ID「%d」の登録完了】】-------------\n\n",*t+1);	
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
 * 会員削除の関数 
 * @param (*pre_member)		最初会員の情報
 * @param (*next_member) 	次の会員の情報
 * @return 		 		会員情報削除の実行
*/
void deleteMember(member **pre_member, member **next_member){
	
	int idCheck;
	int len;
	char choice[SIZE]= {0};
	member *person;
	
	while(1){
		if((*pre_member)->id==0){
			printf("\n----------------現在会員がいません------------------\n\n");
			break;
		}
		printf("-----------------IDを入力してください---------------\n>>");
		len=0;
	
		while((choice[len]=getchar())!='\n'){
			len++;					
		}
		if(len>SIZE){
			printf("会員IDは%d桁以内です。入力し直してください。\n\n",SIZE);
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
			printf("\n--------------【【ID「%d」の削除完了】】-------------\n\n",person->id);				
			if(person->id== (*pre_member)->id){
				if( (*pre_member)->next ==NULL){							/*１つのみでPRE削除*/
					memset((*pre_member), '\0', sizeof(member)+1);
				}else{														/*2以上あってPRE削除*/
					(*pre_member) = person->next;
				}
			}else if(person->id== (*next_member)->id){						/*最後削除*/
				(*next_member) = person->pre;
				(*next_member)->next =NULL;
			}else{															/*真ん中削除*/
				person->pre->next = person->next;
				person->next->pre = person->pre;	
			}	
		}else{
			printf("\n--------------------入力結果------------------------\n");
			printf("会員を探せませんでした。\n\n");
			printf("----------------------------------------------------\n");
		}					
		break;
	}
}

/**
 * 会員修正の関数 
 * @param (*pre_member)		最初会員の情報
 * @return 		 		修正した会員情報
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
			printf("\n----------------現在会員がいません------------------\n\n");
			break;
		}
		printf("-----------------IDを入力してください---------------\n>>");
		len=0;
	
		while((choice[len]=getchar())!='\n'){
			len++;					
		}
		if(len>SIZE){
				printf("会員IDは%d桁以内です。入力し直してください。\n\n",SIZE);
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
			printf("\n------------【【ID「%d」の修正完了】】---------------\n",person->id);	
				
			return new_member;
		}else{
			printf("\n--------------------入力結果------------------------\n");			
			printf("会員を探せませんでした。\n\n");
			printf("----------------------------------------------------\n");	
			return person;
		}		
	}
	return 0;
}

/**
 * すべての会員情報の表示関数 
 * @param (*pre_member)		最初会員の情報
 * @return 		 		すべての会員情報の出力
*/
void findAll(member **pre_member){
	
	member *person;	
	while(1){
		if(  (*pre_member)->id==0){
			printf("\n----------------現在会員がいません------------------\n\n");
			break;
		}
		else{
			person = (*pre_member);
			printf("\n--------------------会員一覧------------------------\n");			
			while (person != NULL) {
			    printf("ID:%d\t", person->id);
			    printf("名前:%s\n", person->name);
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
 * 指定IDの会員情報の表示関数 
 * @param (*pre_member)		最初会員の情報
 * @return 		 		指定IDの会員情報の出力
*/
void findMemberById(member **pre_member){
	
	member *person;	
	int idCheck;
	int len;
	char choice[SIZE]= {0};
	
	while(1){	
		if( (*pre_member)->id==0){
			printf("\n----------------現在会員がいません------------------\n\n");
			break;
		}	
		else{
		printf("-----------------IDを入力してください---------------\n>>");
			len=0;
			
			while((choice[len]=getchar())!='\n'){
				len++;					
			}
			if(len>SIZE){
					printf("会員IDは%d桁以内です。入力し直してください。\n\n",SIZE);
					toEmpty(choice);
					continue;
			}
			
			idCheck=atoi(choice);
			
			person = (*pre_member);
			len=0;
			printf("\n--------------------会員情報------------------------\n");
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
				printf("名前:%s\n", person->name);
				printf("住所:%s\n", person->address);
				printf("生年月日:%s\n", person->birth);						
				printf("電話番号:%s\n", person->tel);							
			}else{
				printf("会員を探せませんでした。\n\n");
			}		
			
			printf("----------------------------------------------------\n");			
			break;
		}
	}
}

/**
 * 全角文字の検定関数
 * @param (*input) 入力分を保管している配列
 * @param (*j)	 添え数（名前と住所）
 * @return 1 	 全角の文字ではなかった場合、エラー発生
 * @return 2 	 数字入力の場合、エラー発生
 * @return 3 	 記号入力の場合、エラー発生
 * @return 4 	 スペースで始まる入力の場合、エラー発生
 * @return 0 	 入力に問題なく、次に進む
*/
int fwidthCheck(char *input,int *j){
		
	int l;
	int k = strlen(input);
	wchar_t *wc;
	wchar_t exceptName[] =  L"ー～＝￥＾＜＞＋｜＃＆";
	wchar_t exceptAddress[] =  L"～＝￥＾＜＞＋｜＃＆";
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
		printf("\n半角は入力できません。\n入力し直してください\n>>");
		free(wc);
		return 1;
	}
	
	for (l=0 ; l< p ; l++) {
		
		symbol = 0;
		space=0;
		
		if( l==0 && wc[l]==L'　'){
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
			printf("\n数字は入力できません。\n入力し直してください\n>>");
			free(wc);
			return 2;
		}else if( space!=0 || iswspace(input[l]) ){
			printf("\n1桁目はスペースが入力できません。\n入力し直してください\n>>");
			free(wc);
			return 4;
		}else if(  iswpunct(wc[l]) || symbol!=0 ){
			
			if( (wc[l]==L'ー' && *j==1) || iswdigit(wc[l-1])){
				continue;
			}else{
				printf("\n記号は入力できません。\n入力し直してください\n>>");
				free(wc);
				return 3;
			}
		}else if( wc[l] < 0x100  ){
			printf("\n全角のみ入力できます。\n入力し直してください\n>>");
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
 * 半角文字の検定関数
 * @param (*input) 入力分を保管している配列
 * @param (*j)	 添え数（生年月日と電話番号）
 * @return 1 	 半角や記号の入力が間違っている場合、エラー発生
 * @return 2 	 '/'の入力が間違っている場合、エラー発生
 * @return 3 	 '-'の入力が間違っている場合、エラー発生
 * @return 4 	 生年月日は必ず10桁で入力、でないとエラー発生
 * @return 0 	 入力に問題なく、次に進む
*/
int numberCheck(char *input,int *j){
	
	int slashCount=0;
	int barCount=0;
	int k;
	int len=strlen(input);
	
	if(len==0)return 0;
	
	if(*j==2 && len!=BIRTH_SIZE-1){
		printf("\n%d桁の半角で正しく入力してください。\n>>",BIRTH_SIZE-1);
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
					printf("\n半角や記号の入力が間違っています。\n入力し直してください\n>>");
					/*free(input);*/
					return 1;
				}
			}
		}
		if(*j==2 && (slashCount!=2 || input[4] !='/' || input[7]!='/') ){		/* '/'が2つじゃなかった場合、入力し直すようにするため*/
			printf("\n'/'の入力が間違っています。\n入力し直してください\n>>");
			return 2;
		}
		else if(*j==3 && (barCount!=2 || (strstr(input, "--") != NULL) 
				|| input[0]=='-' || input[len-1]=='-' ) ){						/* '－'が2つじゃなかった場合、入力し直すようにするため*/
			printf("\n'-'の入力が間違っています。\n入力し直してください\n>>");
			return 3;
		}
	}
	return 0;
}


/**
 * 文字列の長さ制限チェック関数
 * @param (*i) 	添え数
 * @param (*j)	添え数
 * @return 1 	文字列数を超え、エラー発生
 * @return 0 	 入力に問題なく、次に進む 
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
			printf("\n文字を%d桁に制限してます。\n入力し直してください\n>>",maxSize-1);	
		}
		else {
			printf("\n文字を%d桁に制限してます。\n入力し直してください\n>>",maxSize/2);
		}		
		return 1;
	}
	return 0;
}

/** 
 * 却下された入力分を削除する関数
 *（オーバーフロー防止）
 * @param (*input) 入力分を保管している配列
 * @return (*input)配列の初期化
*/
void toEmpty(char *input){
	int j = 0;		/*添え数*/
	while (input[j] != '\0') {
	  input[j] = '\0';
	  j++;
	}
}
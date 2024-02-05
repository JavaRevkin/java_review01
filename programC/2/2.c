#include <stdio.h>
#include <string.h>
#include <locale.h>
#include <wchar.h>
#include <stdlib.h>
#include <wctype.h>
#include <ctype.h>

/**
 * @file 2-2.c
 * @brief 「名前」「住所」「生年月日」「電話番号」の四つの項目を持った構造体を定義し、
 *  		１０件分のデータを入力してそれぞれを出力するプログラム。
 * @author Lee Hyeongbeen
 * @date 2024/1/22
*/

#define NAME_SIZE 21		/* 名前の最大文字数（全角なので*2）*/
#define ADD_SIZE 129		/* 住所の最大文字数（全角なので*2）*/
#define BIRTH_SIZE 11		/* 生年月日の最大文字数 */
#define TEL_SIZE 17		/* 電話番号の最大文字数 */
#define TIMES 10			/* 入力件数分*/

/**
 * 構造体
 * @param (*name)		名前の配列
 * @param (*address) 	住所の配列
 * @param (*birth) 	生年月日の配列
 * @param (*tel) 		電話番号の配列
*/
struct Person {
    char name[NAME_SIZE];
    char address[ADD_SIZE];
    char birth[BIRTH_SIZE];
    char tel[TEL_SIZE];
};

/*関数宣言*/
void inputs(char *ch, char *input,struct Person ps[],int *t);
void toEmpty(char *input);
int fwidthCheck(char *input,int *j);
int numberCheck(char *input,int *j);
int sizeCheck(long *i,int *j);

/*メイン*/
void main() {
    

	char ch='\0';					/*入力文字*/
	char input[ADD_SIZE+1]= {0};			/*入力列の保管配列*/
	int t=0;						/*添え数*/
	struct Person ps[TIMES];			/*会員構造体の配列*/
	
	setlocale(LC_ALL, "Japanese"); 
	

	while(t<TIMES){
		inputs(&ch,input, &ps[t],&t);
		printf("\n//////【【%d番目の人の入力完了】】///////",t+1);	
		t++;
	}
	t=0;
	
	printf("\n【%d名入力結果】\n",TIMES);
	
	while(t<TIMES){
		printf("\n【%d番名目の人】\n",t+1);
		printf(" 名前： %s\t|| 住所： %s\t|| 生年月日： %s\t|| 電話番号： %s\n",
     			ps[t].name, ps[t].address, ps[t].birth, ps[t].tel);
		t++;
	}
	printf("\n【終了】\n");
	getchar();
}

/**
 * 入力関数
 * @param (*ch)	 入力文字
 * @param (*input) 入力分を保管している配列
 * @param (*ps)	 会員構造体の配列
 * @param (*t)	 添え数
 * @return 		 会員情報の登録
*/
void inputs(char *ch, char *input, struct Person ps[],int *t){
	
	long i=0;						/*添え数*/	
	int j =0;
	printf("\n////////////////////////////////////////");	
	printf("\n///////【【%d番目人の情報更新】】////////\n",*t+1);
	
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

			*ch =getchar();
			
			/*未入力の場合*/
			if(*ch=='\n'){
				if (i == 0 && j==0) {
		     			printf("\n未入力です。\n入力し直してください\n>> ");
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
								printf("名前入力：%s\n",input);
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
								printf("住所入力：%s\n",input);
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
								printf("生年月日入力：%s\n",input);
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
								printf("電話番号入力：%s\n",input);
								strcpy(ps->tel ,input);
								break;
							}
						default: break;
					}
					toEmpty(input);			/*却下された入力分を削除する関数*/
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
	
	/*メモリー割り当てる*/
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
		}else if( space!=0 || isspace(input[l]) ){
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
					return 1;
				}
			}
		}
		if(*j==2 && (slashCount!=2 || input[4] !='/' || input[7]!='/') ){		/* '/'が2つじゃなかった場合、入力し直すようにするため*/
			printf("\n'/'の入力が間違っています。\n入力し直してください\n>>");
			return 2;
		}
		else if(*j==3 && (barCount!=2 || (strstr(input, "--") != NULL) || input[0]=='-' || input[len-1]=='-' ) ){	/* '－'が2つじゃなかった場合、入力し直すようにするため*/
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
		case 1: maxSize = ADD_SIZE; break;
		case 2: maxSize = BIRTH_SIZE; break;
		case 3: maxSize = TEL_SIZE; break;
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
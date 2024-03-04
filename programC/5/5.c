/**
 * @file 5.c
 * @brief	入力された年月に対応する万年カレンダーを表示するプログラムを作成せよ。
 * @author Lee Hyeongbeen
 * @date 2024/3/1
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2			/* 入力回数の設定 */
#define YEAR 4			/* 年のサイズ */
#define MONTH 2			/* 月のサイズ */
#define MONTH_CHECK(arr) (arr>=1 && arr<=12) ? 0 : 1	/* 月のチェック、1～12か判断 */


/* 関数宣言 */
void toEmpty(char *input);
int onlyNum(char *input);
void inputNumber(char inputs[][YEAR], int nums[TIMES] );


int get_day(int yyyy, int mm);
int get_day_of_month(int yyyy, int mm);
int is_leaf_year(int yyyy);
void print_cal(int start_day, int day_num);


/* メイン */
int main(void){	
	
	
	char inputs[TIMES+1][YEAR]= {0};		/* 入力値の保管配列 */
	int nums[TIMES] = {0};					/* 年と月の保管配列 */
	int start_day;							/* 当月の1日が何曜日か */
	int day_num;							/* 当月の日数値 */
	
	printf("\n\n【開始】\n");
	
	inputNumber(inputs, nums);						/* 年と月の入力 */
	
    day_num = get_day_of_month(nums[0], nums[1]);	/* 当月の日数値の初期化 */
		
	start_day = get_day(nums[0], nums[1]);			/* 当月の1日が何曜日か初期化 */
	
	
	printf("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
	printf("          %d年%d月         \n", nums[0], nums[1]);
		
    print_cal(start_day, day_num);					/* カレンダーの出力 */
	
	printf("\n\n【終了】\n");
	
	getchar();
	return 0;
}

/** 
 * うるう年の判別
 * @param (*yyyy) 	年度
 * @return 1		うるう年である
 * @return 0		うるう年ではない
*/
int is_leaf_year(int yyyy) {
	
	if ( yyyy % 400 == 0 ) {						/* 年が400で割れたら、うるう年である */
		return 1;
	}
	if ( ( yyyy % 100 != 0) && ( yyyy % 4 == 0) ) {	/* 年が100で割れずにで割れたら、うるう年である */
		return 1;
	}
	return 0;										/* でなければすべて、うるう年ではない */
}

/** 
 * 当月の日数を判別
 * @param (*yyyy) 	年度
 * @param (*mm) 	月
 * @return 			当月の日数値
*/
int get_day_of_month(int yyyy,  int mm) {
	
    int day_of_month[13] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	/* 桁番の月ごとの日数値 */
	
    day_of_month[2] += is_leaf_year(yyyy);					/* うるう年であれば2月は29日にさせる */
	
    return day_of_month[mm];								/* 当月の日数値をreturn */
}



/** 
 * yyyy年mm月の1日が何曜日か判断
 * （（（yyyy-1）年までの日数）+（（mm-1）月までの日数）+１日）を７で割り、その数値で曜日を判定
 * @param (*yyyy) 	年度
 * @param (*mm) 	月
 * @return 			1日の曜日情報を【日・月・・土】の順で【0～6】の値をreturn
*/
int get_day(int yyyy, int mm) {
	
	int past = 0;		/* すべての日数値 */
	int y;				/* 年の添え数 */
	int m;				/* 月の添え数 */
	
	for ( y = 1 ; y < yyyy ; y++ ) {			/* （yyyy-1）年までの日数 */
		past = past + 365 + is_leaf_year(y);
	}
	for ( m = 1 ; m < mm ; m++) {				/* （mm-1）月までの日数 */
		past = past + get_day_of_month(yyyy, m);
	}
	return (1 + past) % 7;						/*  yyyy年の(mm)月の1日が何曜日かreturn */
}



/** 
 * カレンダーの出力
 * @param (*start_day) 	1日の曜日の情報
 * @param (*day_num) 	当月の日数
 * @return 				カレンダーの出力
*/
void print_cal(int start_day, int day_num)
{
	
	int i;
	int day;	/* 表示させる日数の添え数 */
	int ke;		/* 当月1日の曜日値の添え数 */
	
    printf("  日  月  火  水  木  金  土\n");
    for ( i = 0 ; i < start_day ; i++ ) {		/* 1日の曜日前までは【空白】でカレンダー表示 */
        printf("    ");
    }
    for ( day = 1, ke = start_day ; day <= day_num ; day++, ke++) {	/* カレンダーにday値にて日値を表示   */
        printf("%4d", day);											/* 1日の曜日値も一緒に増加させ		 */
        if ( ke % 7 == 6 ) {										/* カレンダーの改行を行う			 */
            printf("\n");
        }
    }
	printf("\n\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
}





/** 
 * 文字の入力
 * @param (*inputs) 保管配列
 * @return 			文字の入力後、outputNumber実行
*/
void inputNumber(char inputs[][YEAR], int nums[TIMES]){
	
	int i=0;				/* 入力桁の添え数 				*/
	int j=0;				/* 入力回数の添え数  			*/
	int numCheck;
	
	while (j<TIMES ) {		/* 入力回数設定の内  */
		
		switch (j) {
			case 0 : printf("\n\n年を入力してください\n>>"); break;
			case 1 : printf("\n\n月を入力してください\n>>"); break;
			default: break;
		}
		
		
		while (1) {						/* 入力が正しい時まで */
			inputs[j][i] = getchar();
			
			if (inputs[j][i] == '\n') {			/* 文字列の尾に着いたら */
				if (i == 0) {					/* 未入力の場合、再入力に戻る */
					printf("\nエラー発生\n未入力です。入力し直してください\n>>");
					inputs[j][i] = '\0';		/* 最後入力値を数字に変換 */
					continue;
				} else{
					nums[j] = atoi(inputs[j]);
					if ( j == 1 ) {				/* 月の入力数値が１～１２ではない場合、再入力を求める */
						if ( MONTH_CHECK(nums[j]) ) {
							printf("\nエラー発生\n半角 数字で 1~12以内で入力し直してください\n>>");
							toEmpty(inputs[j]);
							i = 0;
							continue;
						}
					}
					j++;
					i = 0;
					break;
				}
		   	} else if ( i > YEAR-1 &&  j == 0 ) {		/* 年の桁範囲から外れた時、再入力を求める */
				printf("\nエラー発生\n半角 数字で %d以内の数字で入力し直してください\n>>", YEAR);
				toEmpty(inputs[j]);
				while (getchar() != '\n');
				i = 0;
				continue;
			} else if ( i > MONTH-1 && j == 1 ) {		/* 月の桁範囲から外れた時、再入力を求める */
				printf("\nエラー発生\n半角 数字で %d以内の数字で入力し直してください\n>>", MONTH);
				toEmpty(inputs[j]);
				while (getchar() != '\n');
				i = 0;
				continue;
			} else {
				
				numCheck = onlyNum(inputs[j]);	/* 純数字かを判断する関数 			*/
				
				if (numCheck) {					/* 数字ではなかったら、再入力に戻る */
					printf("\nエラー発生\n半角の正数のみ入力できます。入力し直してください\n>>");
					toEmpty(inputs[j]);
					while (getchar() != '\n');
					i = 0;
					continue;
				} else{									/* 入力正常の場合 						*/
					i++;								/* 次の桁入力に進む						*/
				}
			}
		}
	}
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


/** 
 * 数字のみかを判断する関数
 * スペース & '.' & '-' 区分可能
 * @param (*input) 入力分を保管している配列
 * @return 1 : true、数字じゃない文字列で判断、条件文にかかる
 * @return 0 : false、数字のみの文字列で判断、条件文にかからず
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

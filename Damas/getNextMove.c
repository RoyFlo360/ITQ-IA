#include <stdio.h>

#define MAX_REC 300
#define MAX_POS 10000

#define true 1
#define false 0
typedef int bool;

typedef struct{
    bool ul; bool ur; bool dl; bool dr;
} jumpOptions;

jumpOptions getJumps(int index, char square);

long jumpCounts[4][64];
long ul[64];
long ur[64];
long dl[64];
long dr[64];

char TEAM, OTHER;

void countWins(char table[], char team, char other, int count, int countIndex, long recCount);

void copyTable(char original[], char copy[]);
void invertTable(char table[]);
void printTable(char table[]);

double pecks[64];

char History[MAX_POS][64];
long HistoryLength = 0;

int main(int argc, char *argv[]);
int main(int argc, char *argv[]){
    TEAM = *argv[1];
    OTHER = *argv[2];
    
    printf("\n\nhola\n\n");
    FILE *fp;
    char table[64];
    char *filename = "/home/herrkoder/Dropbox/ITK/Semestre7/IA/Damas/table.txt";
    fp = fopen(filename, "r");
    fgets(table, 65, fp);
    fclose(fp);
    printTable(table);
    for(int i = 0; i < 64; i++){
        printf("##\n##Starting peck %i: %c\n##\n\n", i, table[i]);
        ul[i] = 0; ur[i] = 0; dl[i] = 0; dr[i] = 0;
        char square = table[i];
        if(square != TEAM && square != TEAM + 2) continue;
        jumpOptions js = getJumps(i, square);
        if(js.ul){
            int iLeft = i - 9;
            char sLeft = table[iLeft];
            if(sLeft != TEAM && sLeft != TEAM + 2){
                char newTable[64];
                copyTable(table, newTable);
                if(sLeft == '0'){
                    newTable[iLeft] = square;
                    newTable[i] = '0';
                    invertTable(newTable);
                    countWins(newTable, OTHER, TEAM, 0, i, 1);
                }else{
                    int iiLeft = iLeft - 9;
                    char ssLeft = table[iiLeft];
                    if(ssLeft == '0' && getJumps(iLeft, '3').ul){
                        newTable[iiLeft] = square;
                        newTable[iLeft] = '0';
                        newTable[i] = '0';
                        invertTable(newTable);
                        countWins(newTable, OTHER, TEAM, 0, i, 1);
                    }
                }
            }
        }
        if(js.ur){
            int iRight = i - 7;
            char sRight = table[iRight];
            if(sRight != TEAM && sRight != TEAM + 2){
                char newTable[64];
                copyTable(table, newTable);
                if(sRight == '0'){
                    newTable[iRight] = square;
                    newTable[i] = '0';
                    invertTable(newTable);
                    countWins(newTable, OTHER, TEAM, 1, i, 1);
                }else{
                    int iiRight = iRight - 7;
                    char ssRight = table[iiRight];
                    if(ssRight == '0' && getJumps(iRight, '3').ur){
                        newTable[iiRight] = square;
                        newTable[iRight] = '0';
                        newTable[i] = '0';
                        invertTable(newTable);
                        countWins(newTable, OTHER, TEAM, 1, i, 1);
                    }
                }
            }
        }
        if(js.dl){
            int iLeft = i + 7;
            char sLeft = table[iLeft];
            if(sLeft != TEAM && sLeft != TEAM + 2){
                char newTable[64];
                copyTable(table, newTable);
                if(sLeft == '0'){
                    newTable[iLeft] = square;
                    newTable[i] = '0';
                    invertTable(newTable);
                    countWins(newTable, OTHER, TEAM, 2, i, 1);
                }else{
                    int iiLeft = iLeft + 7;
                    char ssLeft = table[iiLeft];
                    if(ssLeft == '0' && getJumps(iLeft, '3').dl){
                        newTable[iiLeft] = square;
                        newTable[iLeft] = '0';
                        newTable[i] = '0';
                        invertTable(newTable);
                        countWins(newTable, OTHER, TEAM, 2, i, 1);
                    }
                }
            }
        }
        if(js.dr){
            int iRight = i + 9;
            char sRight = table[iRight];
            if(sRight != TEAM && sRight != TEAM + 2){
                char newTable[64];
                copyTable(table, newTable);
                if(sRight == '0'){
                    newTable[iRight] = square;
                    newTable[i] = '0';
                    invertTable(newTable);
                    countWins(newTable, OTHER, TEAM, 3, i, 1);
                }else{
                    int iiRight = iRight  + 9;
                    char ssRight = table[iiRight];
                    if(ssRight == '0' && getJumps(iRight, '3').dr){
                        newTable[iiRight] = square;
                        newTable[iRight] = '0';
                        newTable[i] = '0';
                        invertTable(newTable);
                        countWins(newTable, OTHER, TEAM, 3, i, 1);
                    }
                }
            }
        }
        //if(!(i%8)) printf("\n");
        //printf("%i,%i %i %i %i    ", i, js.ul, js.ur, js.dl, js.dr);

    }
    long max = -1;
    int peck = -1;
    char *direction = "";
    for(int z = 0; z < 4; z++){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) if(jumpCounts[z][i*8 + j] > max){
                max = jumpCounts[z][i*8 + j];
                peck = i*8 + j;
                switch(z){
                    case 0: direction = "UpLeft"; break;
                    case 1: direction = "UpRight"; break;
                    case 2: direction = "DownLeft"; break;
                    case 3: direction = "DownLeft"; break;
                }
            }
        }
    }
    printf("Debes mover %d hacia %s", peck, direction);
    
    return 0;
}

jumpOptions getJumps(int index, char square){
    jumpOptions ret = {
        index > 7 && index%8 > 0,
        index > 7 && index%8 < 7,
        square > '2' && index < 56 && index%8 > 0,
        square > '2' && index < 56 && index%8 < 7 
    };
    return ret;
}

void countWins(char table[], char team, char other, int count, int countIndex, long recCount){
    if(recCount == MAX_REC) return;
    if(HistoryLength == MAX_POS) return;
    //check history
    bool onHistory = false;
    for(int i = 0; i < HistoryLength; i++){
        bool equals = true;
        for(int j = 0; j < 64; j++) if(History[i][j] != table[j]) equals = false;
        if(equals){
            onHistory = true;
            break;
        }
    }
    if(onHistory) return;
    else copyTable(table, History[HistoryLength++]);
    //printTable(table);
    //printf("\n\n");
    //check win
    bool win = true;
    for(int i = 0; i < 64; i++) if(table[i] == OTHER || table[i] == OTHER + 2) win = false;
    if(win){
        jumpCounts[count][countIndex]++;
        printf("Peck %d on %d reached a win. Total: %ld\n", countIndex, count, jumpCounts[count][countIndex]);
        return;
    }
    printf("Start countWin: %ld, %lld, %d: %d\n", recCount, HistoryLength, countIndex, jumpCounts[count][countIndex]);
    for(int i = 0; i < 64; i++){
        ul[i] = 0; ur[i] = 0; dl[i] = 0; dr[i] = 0;
        char square = table[i];
        if(square != team && square != team + 2) continue;
        jumpOptions js = getJumps(i, square);
        //printf("%i-%c, %i %i %i %i\n", i, square, js.ul, js.ur, js.dl, js.dr);
        if(js.ul){
            int iLeft = i - 9;
            char sLeft = table[iLeft];
            if(sLeft != team && sLeft != team + 2){
                char newTable[64];
                copyTable(table, newTable);
                if(sLeft == '0'){
                    newTable[iLeft] = square;
                    newTable[i] = '0';
                    invertTable(newTable);
                    countWins(newTable, other, team, count, countIndex, recCount + 1);
                }else{
                    int iiLeft = iLeft - 9;
                    char ssLeft = table[iiLeft];
                    if(ssLeft == '0' && getJumps(iLeft, '3').ul){
                        newTable[iiLeft] = square;
                        newTable[iLeft] = '0';
                        newTable[i] = '0';
                        invertTable(newTable);
                        countWins(newTable, other, team, count, countIndex, recCount + 1);
                    }
                }
            }
        }
        if(js.ur){
            int iRight = i - 7;
            char sRight = table[iRight];
            if(sRight != team && sRight != team + 2){
                char newTable[64];
                copyTable(table, newTable);
                if(sRight == '0'){
                    newTable[iRight] = square;
                    newTable[i] = '0';
                    invertTable(newTable);
                    countWins(newTable, other, team, count, countIndex, recCount + 1);
                }else{
                    int iiRight = iRight - 7;
                    char ssRight = table[iiRight];
                    if(ssRight == '0' && getJumps(iRight, '3').ur){
                        newTable[iiRight] = square;
                        newTable[iRight] = '0';
                        newTable[i] = '0';
                        invertTable(newTable);
                        countWins(newTable, other, team, count, countIndex, recCount + 1);
                    }
                }
            }
        }
        if(js.dl){
            int iLeft = i + 7;
            char sLeft = table[iLeft];
            if(sLeft != team && sLeft != team + 2){
                char newTable[64];
                copyTable(table, newTable);
                if(sLeft == '0'){
                    newTable[iLeft] = square;
                    newTable[i] = '0';
                    invertTable(newTable);
                    countWins(newTable, other, team, count, countIndex, recCount + 1);
                }else{
                    int iiLeft = iLeft + 7;
                    char ssLeft = table[iiLeft];
                    if(ssLeft == '0' && getJumps(iLeft, '3').dl){
                        newTable[iiLeft] = square;
                        newTable[iLeft] = '0';
                        newTable[i] = '0';
                        invertTable(newTable);
                        countWins(newTable, other, team, count, countIndex, recCount + 1);
                    }
                }
            }
        }
        if(js.dr){
            int iRight = i + 9;
            char sRight = table[iRight];
            if(sRight != team && sRight != team + 2){
                char newTable[64];
                copyTable(table, newTable);
                if(sRight == '0'){
                    newTable[iRight] = square;
                    newTable[i] = '0';
                    invertTable(newTable);
                    countWins(newTable, other, team, count, countIndex, recCount + 1);
                }else{
                    int iiRight = iRight  + 9;
                    char ssRight = table[iiRight];
                    if(ssRight == '0' && getJumps(iRight, '3').dr){
                        newTable[iiRight] = square;
                        newTable[iRight] = '0';
                        newTable[i] = '0';
                        invertTable(newTable);
                        countWins(newTable, other, team, count, countIndex, recCount + 1);
                    }
                }
            }
        }
        //printf(": %i\n", count[countIndex]);
    }
}

void copyTable(char original[], char copy[]){
    for(int i = 0; i < 64; i++) copy[i] = original[i];
}
void invertTable(char table[]){
    char copy[64];
    for(int i = 0; i < 64; i++) copy[i] = table[63 - i];
    copyTable(copy, table);
}

void printTable(char table[]){
    for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++) printf("%c\t", table[i*8 + j]);
        printf("\n");
    }
}
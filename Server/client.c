#include <stdio.h> 
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <unistd.h> 
#include <string.h> 
#include "./constansts.h"

void *SendMessage(void * socket){
    int sock= *(int*)socket; //get the number of socket
    char hello[20]; 
    while (1){
        char hello[20]; 
        printf("Digite lo que quiere enviar\n");
        scanf("%s", hello);
        send(sock , hello , strlen(hello) , 0 );
        sleep(1); 

    }
}

void *ReciveMessage(void * socket){
 int new_socket= *(int*)socket;
    while(1){
        char buffer[BUFFERSIZE];
        int x;
        if ((x=read( new_socket , buffer, BUFFERSIZE))>0){
            buffer[x]='\0';
            printf("Recibido: %s\n",buffer);
            fflush(stdout); 
        }
        
        sleep(1);
    }

}
   
int main(int argc, char const *argv[]) 
{ 
    pthread_t t1, t2;
    int sock = 0, valread; 
    struct sockaddr_in serv_addr; 
    char buffer[BUFFERSIZE] = {0}; 
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) 
    { 
        printf("\n Socket creation error \n"); 
        return -1; 
    } 
   
    serv_addr.sin_family = AF_INET; 
    serv_addr.sin_port = htons(PORT); 
       
    // Convert IPv4 and IPv6 addresses from text to binary form 
    if(inet_pton(AF_INET, IP, &serv_addr.sin_addr)<=0)  
    { 
        printf("\nInvalid address/ Address not supported \n"); 
        return -1; 
    } 
   
    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) 
    { 
        printf("\nConnection Failed \n"); 
        return -1; 
    } 
    pthread_create(&t1, NULL, &SendMessage, &sock);
    pthread_create(&t2, NULL, &ReciveMessage, &sock);
    //send(sock , hello , strlen(hello) , 0 ); 
    //printf("Hello message sent\n"); 
    //valread = read( sock , buffer, 1024); 
    //printf("%s\n",buffer ); 
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    return 0; 
} 
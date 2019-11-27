bits 32

global start

extern exit, scanf, fopen, fprintf, printf, fclose
import exit msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fprintf msvcrt.dll
import printf msvcrt.dll
import fclose msvcrt.dll

segment data use32 class=data
    file db "nrx.txt", 0
    mode db 'w+', 0
    fd dd 0
    mesaj1 db 'Secventa:', 10, 0
    mesaj2 db 'File closed', 10, 0
    
    hex db '%x', 32, 0
    dcc db '%d', 0
    dcp db '%d', 10, 0
    
    nr2 dd 0
    
    nr dd 0
    
segment code use32 class=code:
    start:
        push mesaj1
        call [printf]
        add esp, 4
        
        push mode
        push file
        call [fopen]
        add esp, 4
        
        mov [fd], eax
        
        citire:
            push dword nr
            push dword dcc
            call [scanf]
            add esp, 4*2
            
            mov eax, 0
            mov eax, [nr]
            
            cmp eax, dword 0
            je gata_citire
            
            push dword [nr]
            push dword hex
            push dword [fd]
            call [fprintf]
            add esp, 4*3
            
            mov ecx, 31         ; loop
            mov [nr2], byte 0   ; counter
            mov eax, [nr]       ; nr
            mov ebx, 0          ; byte to compare
            nrb:
            mov bl, al
            and bl, 1b
            
            cmp bl, byte 1
            
            je bit
            jne nbit
            bit:
                add [nr2], byte 1
            nbit:
                shl eax, 1
            loop nrb
            
            push dword [nr2]
            push dword dcp
            push dword [fd]
            call [fprintf]
            add esp, 4*3
                
            
        jmp citire
        
        gata_citire:
        
        push dword [fd]
        call [fclose]
        add esp, 4
        
        push mesaj2
        call [printf]
        add esp, 4
        
        ; 2 : 0010 -> 1
        ; 4 : 0100 -> 1
        
        
        
    push dword 0
    call [exit]
    
bits 32

global start

extern exit, scanf, printf
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data
    num_format db "%d letters", 0
    str_format db "%s", 0
    
    len1 equ 120
    sir times len1 db 0
    sir2 times len1 db 0
    
    cnt dd 0
    
segment code use32 class=code:
    ;strlen:
        
     ;   mov esp, sir
      ;  mov eax, 0
        
       ; nr:
         ;   lodsb
        ;    inc eax
        ;loop nr
        ;ret
        
    start:
    
        push sir
        push str_format
        call [scanf]
        add esp, 4*2
        
        mov esi, sir
        mov edi, sir2
        ;mov eax, 0
        
        nr:
            lodsb
            inc byte [cnt]
            cmp al, 0
            je nvm
            
            add al, 1
            stosb
        loop nr
        
        nvm:
        
        dec byte [cnt]
        
        push sir2
        push str_format
        call [printf]
        add esp, 4*2
    
    push dword 0
    call [exit]
    
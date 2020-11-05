bits 32

global fnc

extern exit

import exit msvcrt.dll
    
segment code use32 class=code public
    fnc:
        mov bl, byte [esp+4]
        mov [esp+8], byte 1
    
        
        
        mov ecx, 4
    
        bucla:
            lodsb
            
            cmp bl, al
            
            ja continua
                mov bl, al
                mov [esp+8], cl
                
            continua:
            
        loop bucla
        
        mov al, [esp+8]
        stosb
        
        add [esp+12], bl
    
        ret
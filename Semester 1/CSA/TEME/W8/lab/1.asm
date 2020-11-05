bits 32

global start

extern exit, printf, scanf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll

; An array of dwords is defined in the data segment
; Display each element

segment data use32 class=data
    A dd 0x1, 0x2, 0x3, 0x4
    
    lenA EQU ($-A)/4
    
    msj db "%d, "
    
segment code use32 class=code:
    start:
        MOV ECX, 1
        DEC ECX
        
        bucla:
            push ECX
            
            push dword [A+ECX*4]
            push msj
            call [printf]
            add esp, 4*2
            
            pop ECX
            
            INC ECX
            CMP ECX, 4
            
        JS bucla
        
        
    push dword 0
    call [exit]
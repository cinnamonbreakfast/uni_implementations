bits 32 ; assembling for the 32 bits architecture

global start        

extern exit
import exit msvcrt.dll


; a, b, c, d : word
; (b-c)+(d-a)
segment data use32 class=data
    a dw 10
    b dw 5
    c dw 3
    d dw 7
    x dw 0


segment code use32 class=code
    start:
        MOV AX, [b]
        SUB AX, [c]
        
        MOV BX, [d]
        SUB BX, [a]
        
        ADD AX, BX
        MOV [x], AX
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program

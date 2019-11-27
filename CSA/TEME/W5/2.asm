bits 32 ; assembling for the 32 bits architecture

global start        

extern exit
import exit msvcrt.dll


;(a+d+d)-c+(b+b)

; a-byte
; b-word
; c-double word
; d-qword
segment data use32 class=data
    a db 3
    b dw 3
    c dd 3
    d dq 7


segment code use32 class=code
    start:;(a+d+d)-c+(b+b)
        MOV EAX, [d]
        MOV EDX, [d+4]
        
        ADD EAX, EAX
        ADC EDX, EDX
        
        ADD AL, [a]
        
        SUB EAX, [c]
        
        MOV EBX, [b]
        ADD EBX, [b]
        ADD EAX, EBX
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program

;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)
(include flags.sh)

;doorState
(enum
	doorClosed
	doorOpening
	doorOpen
	doorClosing
)


;Inventory items
(enum
	iNoInv				;0	;Dummy	
	iCreditCard			;1
	iKnife				;2
	iWood				;3
	iGrass				;4
	iSoap				;5
	iMoney				;6
	iLandDeed			;7
	iTowel				;8
	iSpaKeycard			;9
	iDivorceDecree		;10
	iOrchids			;11
	iPenthouseKey		;12
	iWineBottle			;13
	iPanties			;14
	iPantyhose			;15
	iBra				;16
	iDress				;17
	iMarker				;18
	iCoconuts			;19
	iMarijuana			;20
)

;Global Stuff
(define LSL3			0)
(define DEBUG			20) ;this seems to be intended for Al Lowe
(define CHANGE_SCRIPT	21)
(define DEBUG_22		22) ;this seems to be intended for the QA team
(define INVDESC			30)	;text only
(define DYING			40)
(define FALLING			41)
(define LEIING			42)
(define CARVING			43)
(define WEAVING			44)
(define LOCKER			45)
(define PAGENUMS		46)
(define DOOR			50)
(define AUTODOOR		51)
(define GIRL			70)
(define FACE			71)
(define ROBIN_LARRY		80)
(define ROBIN_PATTI		81)
(define BOSSKEY			90)

;Locales and regions
(define VILLAGE 299)
(define FATCITY 399)
(define CASINO_MIRROR 417)
(define CASINO 499)
(define JUNGLE 599)
(define STUDIO 699)

;Ego statuses
(enum
	egoNORMAL			;0
	egoAUTO				;1
	egoFALLING			;2
	egoDRINKWATER		;3
	egoROLLOUT			;4
	egoNAKED			;5
	egoNAKED_CENSORED	;6
	egoLEISURESUIT		;7
	egoTOWEL			;8
	egoSWEATSUIT		;9
	egoNATIVE			;10
	egoSHOWGIRL			;11
	egoSWIMMING			;12
	egoREMOVEPANTYHOSE	;13
)
(define egoINCOMEDYCLUB 16)
(define egoDYING 1001)
(define egoDEAD 1002)
(define egoDROWNING 1003)
(define egoSITTING 1004)
(define egoSUNBATHING 1005)

;EO: The following three enums are all original Sierra names found in TEXT.417
;Showroom states
(enum
	SRshowIsOn
	SRshowDone
	SRcherriOnPhone
	SRknowsAboutDeed
	SRcherriBackstage
	SRstageDoorUnlocked
	SRdone
)
;Newspaper states
(enum
	NSnotYet
	NSpComing
	NSshowroom
	NSpHere
	NSlMissing
)
;Lawyer states
(enum
	LSbusy
	LSfree
	LSwaiting4Deed
	LSdeedReady
	LSneeds500
	LSdivorce
	LSWaiting4Divorce
	LSdivorceReady
	LSdone
)

;Required memory sizes in bytes
(define GaugeSize 2048)
(define InvSize 1024)


; * Events
; Event types
(define evNULL $0000)                      ; Null event
(define evMOUSEBUTTON $0001)               ; Mouse button event
(define evMOUSERELEASE $0002)              ; Mouse button release event
(define evMOUSE $0003)                     ; Mouse button event
(define evKEYBOARD $0004)                  ; Keyboard event
(define evKEYUP $0008)                     ; key up event
(define evMENUSTART $0010)
(define evMENUHIT $0020)
(define evJOYSTICK $0040)                  ; Movement (joystick) event
(define evSAID $0080)                      ; Said event
(define evJOYDOWN $0100)
(define evJOYUP $0200)
(define evNOJOYSTICK $8000)                ; no joystick event
(define evMOUSEKEYBOARD $0005)             ; Mouse/Keyboard event
(define evALL_EVENTS $7fff)                ; All events
(define evPEEK $8000)
; Event messages
; These are the  set bits, you must and the
; message with these masks to check them.
(define emRIGHT_SHIFT 1)
(define emLEFT_SHIFT 2)
(define emSHIFT 3)
(define emCTRL 4)
(define emALT 8)
(define emSCR_LOCK 16)
(define emNUM_LOCK 32)
(define emCAPS_LOCK 64)
(define emINSERT 128)
(define emLEFT_BUTTON 1)
(define emRIGHT_BUTTON 3)
;
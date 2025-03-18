;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)
(use User)

(public
	rm250 0
)

(local
	nearSteps
	cantGoThere
	nearCasino
	nearOffice
	sharpenCycles
	[plot 200]
)
(procedure (PrintPlot &tmp t)
	(Print @plot
		#at 10 5
		#width 290
		#time (= t (PrintDelay @plot))
		#dispose
	)
	(return (+ 3 t))
)

(instance rm250 of Room
	(properties
		picture 250
		south 260
	)
	
	(method (init)
			

		
		(if (and (ego has: iKnife) (== ((Inventory at: iKnife) view?) 2))
			(Load VIEW 251)
			(Load VIEW 709)
			(Load VIEW 2)
			(Load SOUND 250)
		)
		(if (== currentStatus egoROLLOUT)
			(self style: IRISOUT)
		)
		(super init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
;;;			(DrawRect 93 214 175 189 1)
		(self setScript: RoomScript)
		(if (not (Btst fFired))
			(Load VIEW 53)
		)
		(if (and (Btst fFired) (not (Btst fCredits250)))
			(Load VIEW 252)
			(aCredit1 init:)
			(aCredit2 init:)
		)
		(if (> machineSpeed 16)
			(aFountain init:)
		)
		(cond 
			((== prevRoomNum 305)
				(ego posn: 2 186)
				(= nearOffice TRUE)
			)
			((== prevRoomNum 220)
				(ego posn: 2 122)
			)
			((== prevRoomNum 253)
				(ego posn: 317 125)
			)
			((== prevRoomNum 260)
				(ego posn: 154 187 loop: 3)
			)
			(else
				(= nearSteps TRUE)
				(ego posn: 317 118 loop: 1)
			)
		)
		(NormalEgo)
		(if nearSteps
			(ego setPri: 11 observeControl: cYELLOW init:)
		else
			(ego observeControl: cLMAGENTA init:)
		)
		
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(cond 
				((& (ego onControl:) cBLUE)
					(curRoom newRoom: 305)
				)
				((& (ego onControl:) cCYAN)
					(curRoom newRoom: 220)
				)
				((& (ego onControl:) cRED)
					(if nearSteps
						(curRoom newRoom: 400)
					else
						(curRoom newRoom: 253)
					)
				)
			)
		)
		(cond 
			((== nearSteps -1))
			((& (ego onControl:) cLRED)
				(= nearSteps TRUE)
			)
			((& (ego onControl:) cLCYAN)
				(= nearSteps FALSE)
			)
		)
		(if (== nearSteps TRUE)
			(ego setPri: 11 observeControl: cYELLOW ignoreControl: cLMAGENTA)
		)
		(if (== nearSteps FALSE)
			(ego
				setPri: -1
				ignoreControl: cYELLOW cRED
				observeControl: cLMAGENTA
			)
		)
		(if
			(and
				(& (ego onControl:) cBLUE)
				(or playingAsPatti (!= currentStatus egoNORMAL))
			)
			(if (not nearOffice)
				(= nearOffice TRUE)
				(ego
					posn: (ego xLast?) (ego yLast?)
					setMotion: 0
					observeControl: cBLUE
				)
				(if playingAsPatti
					(Print 250 0)
				else
					(Print 250 1)
				)
				(Animate (cast elements?) FALSE)
			)
		else
			(= nearOffice FALSE)
		)
		(if
			(and
				(& (ego onControl:) cRED)
				nearSteps
				(!= currentStatus egoSHOWGIRL)
				(!= currentStatus egoNORMAL)
			)
			(if (not nearCasino)
				(= nearCasino TRUE)
				(ego
					posn: (ego xLast?) (ego yLast?)
					setMotion: 0
					observeControl: cRED
				)
				(Print 250 2)
				(Animate (cast elements?) FALSE)
			)
		else
			(= nearCasino FALSE)
		)
		(if (& (ego onControl:) cGREEN)
			(if (not cantGoThere)
				(= cantGoThere TRUE)
				(ShakeScreen 1 shakeSRight)
				(Print 250 3)
				(Print 250 4 #at -1 144)
			)
		else
			(= cantGoThere FALSE)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fFired))
					(= cycles 25)
				)
			)
			(1
				(Format @plot 250 15)
				(= seconds (PrintPlot))
				(aCredit1 view: 53 posn: 0 156 setCycle: Forward init:)
				(= seconds 13)
			)
			(2
				(Format @plot 250 16)
				(= seconds (PrintPlot))
			)
			(3
				(Format @plot 250 17)
				(= seconds (PrintPlot))
			)
			(4
				(Bset fFired)
				(Format @plot 250 18)
				(= seconds (PrintPlot))
			)
			(5
				(aCredit1 dispose:)
				(= seconds 0)
			)
			(6
				(Ok)
				(HandsOff)
				(= nearSteps -1)
				(ego illegalBits: 0 setMotion: MoveTo 278 134 self)
			)
			(7
				(ego
					view: 709
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(8
				(Print 250 19 #icon 2 0 0)
				(= sharpenCycles 0)
				(= seconds 2)
				(= saveSpeed (theGame setSpeed: 6))
			)
			(9
				(soundFX number: 250 loop: 1 play:)
				(ego view: 251 cel: 0 setCycle: EndLoop)
				(= cycles 7)
				(if (< (++ sharpenCycles) 11)
					(-- state)
				)
			)
			(10
				((Inventory at: iKnife) view: 21)
				(Format ((Inventory at: iKnife) name?) 250 20) ;fix
;;;				((Inventory at: iKnife) name: {Sharpen knife}) ;ENGLISH
;;;				((Inventory at: iKnife) name: {Cuchillo Afilado}) ;SPANISH
;;;				((Inventory at: iKnife) name: 250 20) ;ENGLISH
;;;				((Inventory at: iKnife) name: 250 20) ;SPANISH
				(soundFX stop:)
				(theGame setSpeed: saveSpeed changeScore: 50)
				(ego view: 709 loop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(11
				(NormalEgo)
				(= nearSteps FALSE)
				(Print 250 21)
				(Print 250 22)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event claimed: TRUE)
			(cls)
			(self cue:)
		)
		(if (event claimed?)
			(return)
		)
	
;		(if (Said 'caress,sharpen/ginsu>')
;			(cond 
;				((not (ego has: iKnife))
;					(DontHave)
;				)
;				((== ((Inventory at: iKnife) view?) 21)
;					(ItIs)
;				)
	;			((Said '/[/noword]')
	;				(Print 250 5)
	;			)
	;			((Said '//fountain,barstool,barstool')
	;				(Print 250 6)
	;			)
	;			((not (Said '//stair,carpet'))
	;				(Print 250 7)
	;			)
	;			((not nearSteps)
	;				(Print 250 8)
	;			)
	;			((!= currentStatus egoNORMAL)
	;				(GoodIdea)
	;			)
	;			(else
	;				(self changeState: 6)
	;			)
	;		)
	;		(event claimed: TRUE)
	;	)
	;	(if (Said 'look>')
		;	(cond 
	;			((Said '/palm,bush,carpet')
	;				(Print 250 9)
;				)
;				((Said '/cannibal,office')
;					(if playingAsPatti
;						(Print 250 10 currentEgo)
;					else
;						(Print 250 11)
;					)
;				)
;				((Said '/water,cascade,fountain')
;					(Print 250 12)
;				)
;				((Said '/stair,exit,carpet')
;					(Print 250 13)
;				)
;				((Said '[/area]')
;					(Print 250 14)
;				)
		;	)
			;)
			
		(if 
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))
			)
				(if
					(and ;fountain
						(> (event x?) 180) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 243) ;x2 
						(> (event y?) 107) ;y1
						(< (event y?) 152) ;y1
					)
						(event claimed: TRUE)
						(switch theCursor
							(2 ;Knife normal
								(Print 250 6)
							)
							(998 ;LOOK			
								(Print 250 12)
							)
							(else
								(event claimed: FALSE)
							)
						)
			)
			
			(if
					(and ;background
						(> (event x?) 1) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 21) ;y1
						(< (event y?) 189) ;y1
					)
				(event claimed: TRUE)
					(switch theCursor					
						(998 ; Look
								(Print 250 14)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)
			
			(if (ClickedInRect 275 317 101 137 event) ;stairs
				(event claimed: TRUE)
				(switch theCursor
					(998 ;LOOK
						(Print 250 13)
					)
					(2 ;GINSU_KNIFE
						(cond 
							((not (ego has: iKnife))
								(DontHave)
							)
							((== ((Inventory at: iKnife) view?) 21)
								(ItIs)
							)
							((not nearSteps)
								(Print 250 8)
							)
							((!= currentStatus egoNORMAL)
								(GoodIdea)
							)
							(else
								(self changeState: 6)
							)
						)
					)
					(999 ; WALK
							(ego setMotion: MoveTo 322 100)
							
						)
					(else
						(event claimed: FALSE)
					)
				)
			)
			(if
					(and ;exitroom to beach 93 214 175 189 1
						(> (event x?) 93) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 214) ;x2 
						(> (event y?) 175) ;y1
						(< (event y?) 189) ;y1
					)
				(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo 146 195)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)			
				
				(if
					(and ;exitroom to casino
						(> (event x?) 312) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 318) ;x2 
						(> (event y?) 103) ;y1
						(< (event y?) 151) ;y1
					)
				(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo 319 126)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)					
				
				(if
					(and ;exitroom to jungle 0 10 104 124
						(> (event x?) 0) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 10) ;x2 
						(> (event y?) 104) ;y1
						(< (event y?) 124) ;y1
					)
				(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo -4 115)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)					
				
					(if
					(and ;exitroom to Natives Inc 0 7 151 166
						(> (event x?) 0) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 7) ;x2 
						(> (event y?) 151) ;y1
						(< (event y?) 166) ;y1
					)
				(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo -5 172)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)				
				
						(if
					(and ;exitroom to Toilets(DrawRect 290 319 78 114 12)
						(> (event x?) 290) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 78) ;y1
						(< (event y?) 114) ;y1
					)
				(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo 322 115)
						;	(curRoom newRoom: 253)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)			
		
				))

			
			
			
			
		)


(instance aFountain of Prop
	(properties
		y 148
		x 194
		view 250
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self isExtra: 1 setCycle: Forward setPri: 11)
	)
)

(instance aCredit1 of Prop
	(properties
		y 131 ;fix 131
		x 240 ;fix 288
		view 252
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors:)
	)
)

(instance aCredit2 of Prop
	(properties
		y 154 ;fix 154
		x 240 ;fix 288
		view 252
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors: setScript: CreditsScript)
	)
)

(instance CreditsScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(aCredit1 setCycle: EndLoop)
				(= cycles 13)
			)
			(2
				(aCredit2 setCycle: EndLoop)
				(= cycles 16)
			)
			(3
				(aCredit2 setCycle: BegLoop self)
			)
			(4
				(aCredit2 loop: 2 setCycle: EndLoop)
				(= cycles 16)
			)
			(5
				(aCredit2 setCycle: BegLoop self)
			)
			(6
				(aCredit2 loop: 3 setCycle: EndLoop)
				(= cycles 16)
			)
			(7
				(aCredit2 setCycle: BegLoop self)
			)
			(8
				(aCredit2 loop: 4 setCycle: EndLoop)
				(= cycles 16)
			)
			(9
				(Bset fCredits250)
				(aCredit1 setCycle: BegLoop)
				(aCredit2 setCycle: BegLoop self)
			)
			(10
				(aCredit1 dispose:)
				(aCredit2 dispose:)
			)
		)
	)
)
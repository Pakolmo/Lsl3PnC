;;; Sierra Script 1.0 - (do not remove this comment)
(script# 235)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use System)
(use User)

(public
	rm235 0
)

(instance rm235 of Room
	(properties
		picture 235
		west 230
	)
	
	(method (init)

		(if (InRoom iOrchids)
			(Load VIEW 236)
		)
		(super init:)
		(self setRegions: 41 setScript: RoomScript)
		(ego posn: 43 124 loop: 0 init:)
		(NormalEgo)
		(User canInput: FALSE canControl: TRUE) ;add quitar teclado
		(if currentStatus
			(ego observeControl: cBLUE)
		)
		
;;;			(DrawRect 76 100 26 90 12) 
;;;			(DrawRect 45 61 116 137 11)
;;;			(DrawRect 215 297 26 125 13)
;;;			(DrawRect 110 205 20 188 14)
	)
)

(instance RoomScript of Script
	(method (doit)
		(if (and (& (ego onControl:) cBLUE) (== currentStatus egoNORMAL))
			(NotifyScript FALLING 2 300)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					setMotion: MoveTo (if (& (ego onControl:) cCYAN) 99 else 235) 96 self
				)
			)
			(2
				(ego
					get: iOrchids
					view: 236
					loop: (if (< (ego x?) 160) 1 else 0)
					cel: 0
					cycleSpeed: 1
					setCycle: Forward
				)
				(= cycles 44)
			)
			(3
				(cond 
					((and (== orchidMinutes 1) (== orchidSeconds 0))
						(Print 235 12)
					)
					((Btst fGotOrchide)
						(Print 235 13)
					)
					(else
						(Bset fGotOrchide)
						(theGame changeScore: 25)
						(Print 235 14)
					)
				)
				(NormalEgo)
				(SetOrchidTimer 1 10 0)
				((Inventory at: iOrchids) view: 11)
				(Format ((Inventory at: iOrchids) name?) {Orquideas}) ;SPANISH
		;;;		(Format ((Inventory at: iOrchids) name?) {Orquids}) ;ENGLISH
			)
		)
	)
	
	(method (handleEvent event)
	;	(if (or (!= (event type?) saidEvent) (event claimed?))
		(if (event claimed?)
			(return)
		)
		(cond 
			((Said 'look<up')
				(Print 235 0)
			)
			((Said 'look<down')
				(Print 235 1)
				(Print 235 2 #at -1 144)
			)
			((Said 'look>')
				(cond 
					((Said '[/area]')
						(Print 235 3)
					)
					((Said '/carpet,carpet')
						(Print 235 4)
					)
					((Said '/ceiling,air')
						(Print 235 0)
					)
					((Said '/camp,beach,bay,water')
						(Print 235 5)
						(Print 235 2 #at -1 144)
					)
					((Said '/bush')
						(Print 235 6)
					)
					((Said '/flower,boulder,wall')
						(Print 235 7)
					)
				)
			)
			((Said 'get/bush')
				(Print 235 8)
			)
			((Said 'get,pick/flower')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(playingAsPatti
						(Print 235 9)
					)
					(
						(and
							(not (& (ego onControl:) cGREEN))
							(not (& (ego onControl:) cCYAN))
						)
						(Print 235 10)
					)
					((and (== orchidMinutes 1) (== orchidSeconds 0))
						(self changeState: 1)
					)
					((not (InRoom iOrchids))
						(Print 235 11)
					)
					(else
						(self changeState: 1)
					)
				)
			)
		)
		(if 
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))
			)
		(if
				(and ;exit room 45 61 116 137
						(> (event x?) 45) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 61) ;x2 
						(> (event y?) 116) ;y1
						(< (event y?) 137) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo -3 125)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)
			
		(if
				(and ;orchidea sun 76 100 26 90
						(> (event x?) 76) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 100) ;x2 
						(> (event y?) 26) ;y1
						(< (event y?) 90) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(995 ; Take
						(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(playingAsPatti
						(Print 235 9)
					)
					(
						(and
							(not (& (ego onControl:) cGREEN))
							(not (& (ego onControl:) cCYAN))
						)
						(Print 235 10)
					)
					((and (== orchidMinutes 1) (== orchidSeconds 0))
						(self changeState: 1)
					)
					((not (InRoom iOrchids))
						(Print 235 11)
					)
					(else
						(self changeState: 1)
					)
				)
						)
						(998 ;look
							(Print 235 7)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)			
		
		(if
				(and ;orchidea not sun 215 297 26 125
						(> (event x?) 215) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 297) ;x2 
						(> (event y?) 26) ;y1
						(< (event y?) 125) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(995 ; Take
						(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(playingAsPatti
						(Print 235 9)
					)
					(
						(and
							(not (& (ego onControl:) cGREEN))
							(not (& (ego onControl:) cCYAN))
						)
						(Print 235 10)
					)
					((and (== orchidMinutes 1) (== orchidSeconds 0))
						(self changeState: 1)
					)
					((not (InRoom iOrchids))
						(Print 235 11)
					)
					(else
						(self changeState: 1)
					)
				)
						)
						(998 ;look
							(Print 235 3)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)			
		
		
		
			(if
				(and ;ROOMAREA 110 205 20 188
						(> (event x?) 110) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 205) ;x2 
						(> (event y?) 20) ;y1
						(< (event y?) 188) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					

						(998 ;look
							(Print 235 5)
							(Print 235 2 #at -1 144)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)			
		
			
		
		
		
		
		
		
		
		
		
		
		)
		
		
		
		
		
		
		
	)
)
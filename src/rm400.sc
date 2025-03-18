;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)
(use Motion) ;ADD
(use User)

(public
	rm400 0
)

(instance rm400 of Room
	(properties
		picture 400
		horizon 83
		north 410
		east 460
		south 250
		west 250
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(switch prevRoomNum
			(460
				(ego posn: 318 162)
			)
			(410
				(if (< (ego x?) 111)
					(ego x: 111)
				)
				(if (> (ego x?) 210)
					(ego x: 210)
				)
			)
			(else
				(ego posn: 159 188)
			)
		)
		(ego init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		;(if (or (!= (event type?) saidEvent) (event claimed?))
			(if (event claimed?)
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/burn')
					(Printf 400 0 currentEgo)
				)
				((Said '[/area]')
					(Print 400 1)
				)
			)
		)
		(cond
						(
				(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))
			)
			
							(if
				(and ;exit room down (DrawRect 10 292 180 189)
						(> (event x?) 10) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 292) ;x2 
						(> (event y?) 180) ;y1
						(< (event y?) 189) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo 143 192)
						)
						(else
							(event claimed: FALSE)
						)
					)
			
		
			
				)
			
			
			
			
			
			
			
			
				(if
				(and ;exit room
						(> (event x?) 312) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 136) ;y1
						(< (event y?) 189) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo 321 159)
						)
						(else
							(event claimed: FALSE)
						)
					)
			
		
			
				)
				
				
								(if
				(and ;background
						(> (event x?) 31) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 272) ;x2 
						(> (event y?) 21) ;y1
						(< (event y?) 179) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(998 ; LOOK
							(Print 400 1)
						)
						(else
							(event claimed: FALSE)
						)
					)
			
		
			
				)
				
				
				
				
				
				
				
				
				
				
				)
)))
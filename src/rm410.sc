;;; Sierra Script 1.0 - (do not remove this comment)
(script# 410)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)
(use Motion) ;ADD
(use User)

(public
	rm410 0
)

(local
	local0
)
(instance rm410 of Room
	(properties
		picture 410
		south 400
	)
	
	(method (init)
		
		(super init:)
				
		(self setScript: RoomScript)
		(NormalEgo)
		(cond 
			((== prevRoomNum 415)
				(ego posn: 59 45)
			)
			((== prevRoomNum 416)
				(ego posn: 263 45)
			)
			(else
				(ego posn: 159 188)
			)
		)
		(ego init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
;;;		(DrawRect 1 319 21 169 13) ;test
;;;		(DrawRect 1 310 168 189 10) ;test
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)

		(cond 
			((& (ego onControl: origin) cBLUE)
				(curRoom newRoom: 415)
			)
			((& (ego onControl: origin) cGREEN)
				(curRoom newRoom: 416)
			)
		)
	)
	
	(method (handleEvent event)
	;	(if (or (!= (event type?) saidEvent) (event claimed?))
		(if (event claimed?)
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/art,body,naked,babe,art')
					(Print 410 0)
				)
				((Said '/column')
					(Print 410 1)
				)
				((Said '/rail')
					(Print 410 2)
				)
				((Said '/stair')
					(Print 410 3)
				)
				((Said '/curtain')
					(Print 410 4)
				)
				((Said '[/area]')
					(Print 410 5)
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
					(and ;exitroom down 1 310 168 189
						(> (event x?) 1) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 310) ;x2 
						(> (event y?) 168) ;y1
						(< (event y?) 189) ;y1
					)
				(event claimed: TRUE)
					(switch theCursor					
						(999 ;WALK
							(ego setMotion: MoveTo 160 195)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)
				(if
				(and ;background 		1 319 21 169
						(> (event x?) 1) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 21) ;y1
						(< (event y?) 169) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						
						(998 ;look
								(switch (Random 0 5)
									(0 (Print 410 0))
									(1 (Print 410 1))
									(2 (Print 410 2))
									(3 (Print 410 3))
									(4 (Print 410 4))
									(5 (Print 410 5))
								)
						)
						(else
							(event claimed: FALSE)
						)
					)
				
				)
		
			))
		

		
	)
)

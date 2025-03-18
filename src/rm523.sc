;;; Sierra Script 1.0 - (do not remove this comment)
(script# 523)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)
(use Motion) ;ADD
(use User)

(public
	rm523 0
)

(instance rm523 of Room
	(properties
		picture 523
		west 520
	)
	
	(method (init)
		(super init:)
		(self setRegions: 41 setScript: RoomScript)
		(NormalEgo)
		(ego init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
	)
)

(instance RoomScript of Script
	(method (doit)
		(if (and (& (ego onControl:) cBLUE) (== currentStatus egoNORMAL))
			(NotifyScript FALLING 2 188)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		;(if (or (!= (event type?) saidEvent) (event claimed?))
		(if (event claimed?)
			(return)
		)
		(cond 
			((Said 'use,attach/bra')
				(cond 
					((not (ego has: iBra))
						(DontHave)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(Print 523 0)
					)
				)
			)
			((Said 'use,attach/hose')
				(if (not (ego has: iPantyhose))
					(Print 523 1)
				else
					(Print 523 2)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/boulder')
						(Print 523 3)
					)
					((Said '[/area]')
						(Print 523 4)
					)
				)
			)
			((Said '(up<climb),climb/boulder,arch')
				(Print 523 5)
			)
			((or (Said '(climb,go)<(down,above)') (Said 'decrease/i'))
				(Print 523 6)
			)
			((Said 'climb')
				(Print 523 7)
				(Print 523 8 #at -1 144)
			)
			((Said 'drag,grasp,get/bush,hemp')
				(Print 523 9)
			)
			((Said 'get,use/palm')
				(Print 523 10)
			)
			((Said '/bush')
				(Print 523 11)
			)
			((Said '/arch')
				(Print 523 12)
			)
			((Said '/flower')
				(Print 523 13)
			)
			((Said 'jump')
				(Print 523 14)
			)
			
						(
				(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))
			)
			(if
				(and ;exit room
						(> (event x?) 0) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 8) ;x2 
						(> (event y?) 103) ;y1
						(< (event y?) 184) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo -4 160)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)
			
					(if
				(and ;background area
						(> (event x?) 1) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 21) ;y1
						(< (event y?) 189) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(998 ; Look
							(switch (Random 1 2)
							(1(Print 523 4)
							)
							(2
							(Print 523 3)
							)
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

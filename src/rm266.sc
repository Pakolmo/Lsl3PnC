;;; Sierra Script 1.0 - (do not remove this comment)
(script# 266)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm266 0
)

(local
	local0
)
(instance rm266 of Room
	(properties
		picture 266
	)
	
	(method (init)
		;(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
		(User canInput: 1 canControl: 1 mapKeyToDir: FALSE)
		(super init:)
		(self setScript: RoomScript)
		(if (< filthLevel 3)
			(addToPics add: atpBikiniTop doit:)
		)
		(addToPics add: atpBikiniBottom doit:)

		
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (== theCursor 999) ;don't allow walk cursor, switch to look
			(theGame setCursor: 998 (HaveMouse))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fLookedAtTawni))
					(= seconds 3)
				)
			)
			(1
				(Bset fLookedAtTawni)
				(Print 266 10)
				(Print 266 11 #at -1 144)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(or 
					(and
						(== (event message?) ENTER)
						(== (event type?) keyDown)		
					)
					(== (event type?) evMOUSEBUTTON)
				)
			)
			(event claimed: TRUE)
			(cls)
			(self cue:)
		)
		(if (event claimed?)	
			(return)
		)
		(cond 
			(
				(or
					(Said 'cease/look')
					(Said 'look<cease')
					(Said 'look/beach,area')
					(Said 'exit,done')
					(Said 'exit,exit,done,exit,go')
				)
				(Ok)
				(curRoom newRoom: 260)
			)
			((Said 'address,ask,say')
				(Print 266 0)
				(curRoom newRoom: 265)
			)
			((Said 'give')
				(Print 266 1)
			)
			((Said 'look>')
				(cond 
					((Said '/babe,body,maller')
						(Print 266 2)
						(Print 266 3)
					)
					((Said '/boob')
						(if (>= filthLevel 3)
							(Print 266 4)
						else
							(Print 266 5)
						)
					)
					((Said '/ass,bottom')
						(Print 266 6)
					)
					((Said '/clit,ball')
						(Print 266 7)
					)
					((Said '/eye,eye')
						(Print 266 0)
						(curRoom newRoom: 265)
					)
					((Said '/calf')
						(Print 266 8)
					)
					((Said '[/area]')
						(Print 266 9)
					)
				)
			)
			((== (event type?) evMOUSEBUTTON)
				(if (& (event modifiers?) emRIGHT_BUTTON) ;is right_click
					(switch theCursor ;current cursor
						(itemIcon
							(theGame setCursor: 991 (HaveMouse)) ;switch to exit intead of walk
							(event claimed: 1)
						)
						(991 ;if exit
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
						)
						(999 ;just incase they have the walk cursor
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(993 ;just incase they have the arrow cursor
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(996
							(theGame setCursor: 994 (HaveMouse))
							(event claimed: 1)
						)
						(998
							(theGame setCursor: 995 (HaveMouse))
							(event claimed: 1)
						)
						(995
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(994
							(if
								(or 
									(== itemIcon 900)
									(== itemIcon 994)
									(== itemIcon 666)
									(== itemIcon 992)
									(== itemIcon 993)
								)
								(theGame setCursor: 991 (HaveMouse))
							else
								(theGame setCursor: itemIcon (HaveMouse))
								(= theCursor itemIcon)
							)
							(event claimed: 1)
						)
					)                                                                  
				else ;left clicks
									(if
					(and ;girl
						
						(> (event x?) 83) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 131) ;x2 
						(> (event y?) 9) ;y1
						(< (event y?) 180) ;y2
					)
						(event claimed: TRUE)
						(switch theCursor					
							(998 ; Look
								(Print 266 0)
								(theGame setCursor: 998) ;set cursor to look before returning
								(curRoom newRoom: 265)
							)
							(996 ; TALK
								(Print 266 1)	
							)
							(995 ;use,take
								(if (>= filthLevel 3)
									(Print 266 4)
								else
									(Print 266 5)
								)
							)		
							(991 ; exit cursor
								(Ok)
								(HandsOff)
								(= theCursor 998)
								(theGame setCursor: 998) ;set cursor to look before returning
								(curRoom newRoom: 260)
							)
							(else
								(event claimed: FALSE)
							;	(theGame setCursor: 991) ;Exit cursor
							)		
						)
					)
				)
			)	
		)
	)
)

(instance atpBikiniTop of PicView
	(properties
		y 56
		x 98
		view 266
	)
)

(instance atpBikiniBottom of PicView
	(properties
		y 91
		x 103
		view 266
		cel 1
	)
)

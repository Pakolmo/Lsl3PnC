;;; Sierra Script 1.0 - (do not remove this comment)
(script# 265)
(include game.sh)
(use Main)
(use Face)
(use Intrface)
(use Game)
(use Invent)
(use System)
(use User)
(public
	rm265 0
)
(synonyms
	(babe maller)
)

(local
	local0
)
(instance rm265 of Room
	(properties
		picture 265
	)
	
	(method (init)
		(if (ego has: iCreditCard)
			(Load VIEW 1)
		)
		(super init:)
		(self setRegions: FACE setLocales: GIRL setScript: RoomScript)
		(NotifyScript 71 1 115 77)
		(NotifyScript 71 2 171 82)
		(NotifyScript 71 3 114 82)
		(NotifyScript 71 4 169 87)
		(NotifyScript 71 5 139 121)
		(NotifyScript 71 6 140 136)
		(= theCursor 998) ;set to look cursor
		(theGame setCursor: 998) ;set to look cursor
				
;;;		(DrawRect 99 132 65 88) ;eye1 99 132 65 88
;;;		(DrawRect 153 195 75 90 1) ;eye2
;;;		(DrawRect 126 153 77 117) ;nose
;;;		(DrawRect 72 86 74 104) ;ear1
;;;		(DrawRect 118 165 128 142 9) ;lips
;;;		(DrawRect 57 231 21 59 10) ;hair
;;;		(DrawRect 241 219 21 185 11) ;hair right side
;;;		(DrawRect 55 240 21 190 14) ;head
;;;		(DrawRect 0 320 21 190) ;anywhere else
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				;(HandsOff)
				(User canInput: 1 canControl: 1)
				(= theCursor 998) ;set to look cursor
				(theGame setCursor: 998) ;set to look cursor
				(EgoSays 265 62 #icon 1 0 0)
				(theGame changeScore: 50)
				((Inventory at: iCreditCard) owner: -1)
				(PersonSays 265 63)
				(= currentStatus egoAUTO)
				(++ state)
				(AnimateFace 2 99)
				(= cycles 50)
			)
			(2
				(Print 265 64)
				(AnimateFace 2 55)
				(= cycles 20) ;was 50

			)
			(3

				(curRoom newRoom: 260)
				(= theCursor 997) ;set to wait cursor
				(theGame setCursor: 997) ;wait to look cursor
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		;(if (or (!= (event type?) saidEvent) (event claimed?))
		(if (event claimed?)
			(return)
		)
		(cond 
			(
				(or
					(Said 'go,get//center,shopping')
					(Said 'go,get/shopping,center')
				)
				(EgoSays 265 0)
				(PersonSays 265 1)
			)
			(
				(or
					(Said '(go<out),date/entertainer')
					(Said 'make/enjoy,date')
					(Said 'let<make/enjoy,date')
					(Said '/make/enjoy')
					(Said 'ask,get,go/area,backdrop,comedian,club,date,exit,bar,show,building,dance')
					(Said 'ask,get,go//area,backdrop,comedian,club,date,exit,bar,show,building,dance')
				)
				(EgoSays 265 2)
				(PersonSays 265 3)
			)
			((Said 'sell')
				(PersonSays 265 4)
				(AnimateFace 2 20)
			)
			((Said '/souvenir')
				(EgoSays 265 5)
				(PersonSays 265 6)
			)
			((Said '/swim,bay,water')
				(EgoSays 265 7)
				(PersonSays 265 8)
			)
			(
				(or
					(Said 'cease/look')
					(Said 'look<cease')
					(Said 'nightstand,(nightstand<up),(get<off,up)')
					(Said 'look/beach,area')
					(Said 'done,exit,exit,done,exit,go')
				)
				(self changeState: 2)
			)
			((Said 'bang')
				(PersonSays 265 9 expletive)
				(AnimateFace 4 8)
			)
			((Said 'show/ball')
				(EgoSays 265 10)
				(PersonSays 265 11)
				(AnimateFace 2 20)
			)
			((Said '/weather')
				(PersonSays 265 12)
			)
			((Said 'make/joke')
				(Print 265 13)
				(Print 265 14 #at -1 144)
				(PersonSays 265 15)
				(AnimateFace 7 22)
			)
			((Said 'enjoy/ya')
				(EgoSays 265 16)
				(PersonSays 265 17)
				(AnimateFace 5 44)
			)
			(
				(or
					(Said '(out<go),dance,date/')
					(Said 'get/babe/building')
					(Said 'ask/babe/date')
					(Said 'go/area,backdrop,comedian,club,date,exit,bar,show,dance')
				)
				(EgoSays 265 18)
				(PersonSays 265 19)
			)
			((Said '/center')
				(EgoSays 265 20)
				(PersonSays 265 21)
			)
			((Said 'address')
				(switch (Random 1 3)
					(1
						(EgoSays 265 22)
						(PersonSays 265 23)
					)
					(2
						(EgoSays 265 24)
						(PersonSays 265 25)
					)
					(else 
						(EgoSays 265 26)
						(PersonSays 265 27)
					)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/calf')
						(Print 265 28)
						(PersonSays 265 29)
						(AnimateFace 3 22)
					)
					((Said '/skin')
						(Print 265 30)
						(Print 265 31 #at -1 144)
					)
					((Said '/boob')
						(if (>= filthLevel 3)
							(Print 265 32)
							(PersonSays 265 33)
						else
							(Print 265 34)
							(PersonSays 265 35)
						)
						(AnimateFace 6 12)
					)
					((Said '/eye')
						(Print 265 36)
					)
					((Said '/nose')
						(Print 265 37)
					)
					((Said '/ear')
						(Print 265 38)
						(Print 265 39 #at -1 144)
					)
					((Said '/lip')
						(Print 265 40)
					)
					((Said '/dicklicker')
						(Print 265 41)
					)
					((Said '/eye')
						(Print 265 42)
					)
					((Said '/hair')
						(Print 265 43)
					)
					((Said '/ass,bottom')
						(Print 265 44)
					)
					((Said '/clit')
						(Print 265 45)
						(PersonSays 265 46)
						(AnimateFace 6 12)
					)
					((Said '[/area,beach,babe]')
						(Print 265 47)
						(Print 265 48 #at -1 144)
						(AnimateFace 2 33)
					)
				)
			)
			((Said 'show>')
				(= i (inventory saidMe:))
				(event claimed: FALSE)
				(cond 
					((Said '[/noword]')
						(Print 265 49)
					)
					(
						(or
							(not i)
							(not (i ownedBy: ego))
						)
						(DontHave)
					)
					((== i (inventory at: iWood))
						(PersonSays 265 50)
						(AnimateFace 4)
					)
					((== i (inventory at: iSoap))
						(PersonSays 265 51)
						(AnimateFace 4)
					)
					((== i (inventory at: iOrchids))
						(PersonSays 265 52)
						(AnimateFace 4)
					)
					((== i (inventory at: iCreditCard))
						(PersonSays 265 53)
					)
					(else
						(PersonSays 265 54)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'give>')
				(= i (inventory saidMe:))
				(event claimed: FALSE)
				(cond 
					((Said '[/noword]')
						(Print 265 55)
					)
					((not i)
						(Print 265 56)
					)
					((not (i ownedBy: ego))
						(DontHave)
					)
					((== i (inventory at: iWood))
						(PersonSays 265 57)
						(AnimateFace 4)
					)
					((== i (inventory at: iSoap))
						(PersonSays 265 58)
						(AnimateFace 4)
					)
					((== i (inventory at: iOrchids))
						(PersonSays 265 59)
						(AnimateFace 4)
					)
					((== i (inventory at: iCreditCard))
						(if (not (ego has: iCreditCard))
							(DontHave)
						else
							(self changeState: 1)
						)
					)
					(else
						(PersonSays 265 60)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'get')
				(PersonSays 265 61)
				(AnimateFace 2 22)
			)
			((== (event type?) evMOUSEBUTTON)
				(if (& (event modifiers?) emRIGHT_BUTTON) ;is right_click
					(switch theCursor ;current cursor
						(itemIcon
							(theGame setCursor: 998 (HaveMouse)) ;switch to exit intead of walk
							(event claimed: 1)
						)
						(991 ;if exit
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
						)
						(999 ;just incase they have the walk cursor
							(theGame setCursor: 998 (HaveMouse))
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
									(== itemIcon 999)
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
						(== theCursor 999)
						(theGame setCursor: 998 (HaveMouse))
						
					)
					(if 
						(== theCursor 995)
						(theGame setCursor: 995 (HaveMouse))
						
					)
					(if 
						(or
							(ClickedInRect 99 132 65 88 event) ;eye1
							(ClickedInRect 153 195 75 90 event) ;eye2
						)
						(if (== theCursor 998) ;clicked look on eyes			
							(Print 265 36)
							(event claimed: TRUE)
						)
					)			
					(if (ClickedInRect 126 153 77 117 event) ;nose
						(if (== theCursor 998) ;clicked look on nose			
							(Print 265 37)
							(event claimed: TRUE)
						)
					)	
					(if (ClickedInRect 72 86 74 104 event) ;ear1
						(if (== theCursor 998) ;clicked look on ear			
							(Print 265 38)
							(Print 265 39 #at -1 144)
							(event claimed: TRUE)
						)
					)					
					(if (ClickedInRect 118 165 128 142 event) ;lips
						(switch theCursor					
							(998 ;Look
								(Print 265 40)
								(event claimed: TRUE)
							)
							(996 ;TALK
								(switch (Random 1 3)
									(1
										(EgoSays 265 22)
										(PersonSays 265 23)
									)
									(2
										(EgoSays 265 24)
										(PersonSays 265 25)
									)
									(3
										(EgoSays 265 26)
										(PersonSays 265 27)
									)
								)
								(event claimed: TRUE)
							)	
						)
					)	
					(if
						(or
							(ClickedInRect 57 231 21 59 event) ;hair top
							(ClickedInRect 241 219 21 185 event) ;hair right side
						)
						(switch theCursor					
							(998 ;Look
								(Print 265 43)
								(event claimed: TRUE)
							)
						)
					)
					(if
						(and
							(ClickedInRect 55 240 20 190 event) ;head
							(== (event claimed?) 0) ;ignore if already clicked on eyes, hair, etc...
						)
						(switch theCursor
							(999 ;Walk
								(event claimed: TRUE)
							)
								
							(998 ;look head
								(Print 265 47)
								(Print 265 48 #at -1 144)
								(AnimateFace 2 33)
								(event claimed: TRUE)
							)
							(995 ;take/hand
								(PersonSays 265 61)
								(AnimateFace 2 22)
								(event claimed: TRUE)
								(theGame setCursor: 995 (HaveMouse))
								
							)
							(996 ;TALK
								(switch (Random 1 3)
									(1
										(EgoSays 265 22)
										(PersonSays 265 23)
									)
									(2
										(EgoSays 265 24)
										(PersonSays 265 25)
									)
									(3
										(EgoSays 265 26)
										(PersonSays 265 27)
									)
								)
								(event claimed: TRUE)
							)
							(3 ;iWood
								(PersonSays 265 57)
								(AnimateFace 4)
								(event claimed: TRUE)
							)
							(5 ;isoap
								(PersonSays 265 58)
								(AnimateFace 4)
								(event claimed: TRUE)
							)
							(9 ;orchids
								(PersonSays 265 59)
								(AnimateFace 4)
								(event claimed: TRUE)
							)
							(1 ;credit card
								(if (not (ego has: iCreditCard))
									(DontHave)
								else
									(= itemIcon 900) ;clear menu inv item pic
									(theGame setCursor: 998 (HaveMouse)) ;clear inv cursor, switch to look
									(HandsOff)
									(self changeState: 1)
								)
								(event claimed: TRUE)
							)
							(991 ;exit
								(HandsOff)
								(self changeState: 2)
								(event claimed: TRUE)
							)
							(else
								(PersonSays 265 60)
								(event claimed: TRUE)
							)
						)
					)
					(if 
						(and
							(ClickedInRect 0 320 21 190 event) ;anywhere else 
							(== (event claimed?) 0) ;ignore if already clicked on head, eyes, hair, etc...
						)
						(switch theCursor
							(995
								(event claimed: TRUE)
							)								
							(999
								(event claimed: TRUE)
							)
							(991 ;exit
								(self changeState: 2)
								(event claimed: TRUE)
							)
							(else
								(theGame setCursor: 991 (HaveMouse)) ;switch to exit cursor if clicked outside of head
								(event claimed: TRUE)
							)
						)
					)
				)
			)
		)
	)
)
;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use DCIcon)
(use Motion)
(use Game)
(use Invent)
(use Menu)
(use Actor)
(use System)
(use Follow) ;ADD
(use User)


(public
	rm420 0
)
(synonyms
	(man guard man man man bouncer)
	(babe babe dale cheri)
)

(local
	[msgBuf 166]
	[titleBuf 22]
	passPage
	inputPassNum
	;EO: Some of these numbers did not decompile right. I got the correct numbers from the manual.
	correctPassNum = [0 0 0 00741 0 55811 30004 0 0 18608 25695 32841 00993 0 0 09170 0 0 49114 33794 0 0 55482 62503]
	talkedToMaitreD
)
(procedure (MaitreDSays theView theLoop theCel)
	(Print @msgBuf
		#at -1 10
;;;		#title {the Maitre d' says} ;English
		#title {el Maitre d' dice} ;Spanish
		#mode teJustCenter
		#icon theView theLoop theCel
	)
	(= msgBuf 0)
)

(instance rm420 of Room
	(properties
		picture 420
		east 415
	)
	
	(method (init)

		(Load SOUND 11)
		(if (ego has: iLandDeed)
			(Load VIEW 7)
		)
		(super init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
		(addToPics
			add: atpPoster1
			add: atpPoster2
			add: atpPhone
			add: atpPodium
			doit:
		)
		(aRope init:)
		(aDoor init:)
		(NormalEgo)
		(ego observeControl: cYELLOW cLMAGENTA init:)
		(self setScript: RoomScript)
		(if
			(or
				(== prevRoomNum 430)
				(== prevRoomNum 431)
				(== showroomState 0)
			)
			(aMaitreD view: 422 setPri: 5 setLoop: 2 init:)
		)
		(if (< showroomState SRknowsAboutDeed)
			(aManager init:)
			(= theDoor aDoor)
		)
		(if
			(or
				(== showroomState SRshowDone)
				(== showroomState SRcherriOnPhone)
				(== showroomState SRknowsAboutDeed)
			)
			(aCherri init:)
			(= theDoor aDoor)
		)
		(cond 
			((== prevRoomNum 440)
				(if (== currentStatus 18)
					(= currentStatus egoNORMAL)
				)
				(TheMenuBar draw:)
				(StatusLine enable:)
				(ego loop: 0 posn: 44 142)
			)
			((== prevRoomNum 435)
				(= currentStatus egoNORMAL)
				(ego
					loop: saveEgoLoop
					posn: (if saveEgoX else 99) (if saveEgoY else 124)
				)
				(aCherri init:)
			)
			((== prevRoomNum 430)
				(RoomScript changeState: 1)
			)
			((== prevRoomNum 431)
				(self style: IRISOUT)
				(RoomScript changeState: 1)
			)
			((> (ego y?) 130)
				(ego posn: 317 188 loop: 1)
			)
			(else
				(ego loop: 1 posn: 309 163)
			)

		)    		
					
;;;		(DrawRect 64 73 73 94 10) ;Only for testing
;;;								(DrawRect 76 240 127 188 1)
	)
	
	(method (newRoom n)
		(if (== theDoor aDoor)
			(= theDoor 0)
			(DisposeScript 421)
			(DisposeScript 422)
		)
		(super newRoom: n)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1
				(TheMenuBar draw:)
				(StatusLine enable:)
				(aRope setCel: 255 stopUpd:)
				(aMaitreD view: 422 posn: 125 109 setPri: 5 init:)
				(NormalEgo 2)
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					posn: 150 94
					setMotion: MoveTo 150 114 self
				)
			)
			(2
				(ego stopUpd:)
				(aRope setCycle: BegLoop)
				(aMaitreD
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 172 105 self
				)
			)
			(3
				(aMaitreD setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(4
				(aMaitreD setCel: 0 stopUpd:)
				(aRope stopUpd:)
				(NormalEgo)
				(ego observeControl: cYELLOW cLMAGENTA)
				(if (== prevRoomNum 430)
					(Print 420 20)
				else
					(Print 420 21)
					(= seconds 2)
				)
			)
			(5
				(Print 420 22)
				(Print 420 23 #at -1 144)
			)
			(6
				(Ok)
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 44 140 self
				)
			)
			(7
				(aDoor setCycle: EndLoop self)
				(ego setLoop: 1)
			)
			(8
				(ego setMotion: MoveTo -3 140 self)
			)
			(9
				(aDoor setCycle: BegLoop self)
			)
			(10
				(soundFX number: 11 loop: 1 play:)
				(= cycles 12)
			)
			(11
				(curRoom newRoom: 440)
			)
			(12
				(HandsOff)
				(aCherri setScript: 0)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 50 143 self
				)
			)
			(13
				(ego loop: 1)
				(= cycles 11)
			)
			(14
				(if (== showroomState SRcherriBackstage)
					(Print 420 24)
					(= seconds 3)
				else
					(= cycles (= seconds 0))
					((aManager script?) cue:)
					(self dispose:)
				)
			)
			(15
				(Print 420 25)
				(Print 420 26)
				(Print 420 27)
				(= seconds 3)
			)
			(16
				(Print 420 28)
				(Print 420 29)
				(= seconds 3)
			)
			(17
				(Print 420 30)
				(if (ego has: iLandDeed)
					(= state 19)
				)
				(= seconds 3)
			)
			(18
				(Print 420 31)
				(= seconds 3)
			)
			(19
				(Print 420 32)
				(Print 420 33)
				(NormalEgo)
				(ego observeControl: cYELLOW cLMAGENTA)
			)
			(20
				(Print 420 34 #icon 7 0 0)
				(PutInRoom iLandDeed)
				(theGame changeScore: 25)
				(= seconds 3)
			)
			(21
				(Print 420 35 #at 10 5 #width 290)
				(= seconds 3)
			)
			(22
				(aDoor setCycle: EndLoop self)
			)
			(23
				(aDoor stopUpd:)
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(24
				(aDoor setCycle: BegLoop self)
			)
			(25
				(soundFX number: 11 loop: 1 play:)
				(cls)
				(Print 420 36)
				(curRoom newRoom: 440)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0) ;add variable
		(if (event claimed?) ;only check if claimed
			(return)
		)
		(cond 
			((Said 'look/art,art')
				(Print 420 0)
			)
			((or (Said 'make/call') (Said 'call') (Said 'use/call'))
				(if (== showroomState SRcherriOnPhone)
					(Print 420 1)
				else
					(Print 420 2)
				)
			)
			((Said '/change')
				(Print 420 3)
			)
			((Said 'unbolt/door')
				(Print 420 4)
			)
			((Said 'open/door')
				(cond 
					((not (& (ego onControl:) cCYAN))
						(NotClose)
					)
					((== currentStatus egoSHOWGIRL)
						(self changeState: 6)
					)
					(else
						(Print 420 5)
					)
				)
			)
			((and (== currentStatus egoSHOWGIRL) (Said '/cloth,cloth'))
				(Print 420 6)
			)
			((Said 'knock')
				(cond 
					((== currentStatus egoSHOWGIRL)
						(Print 420 7)
					)
					((or (Btst fScrewedCherri) (>= showroomState SRdone))
						(Print 420 8)
					)
					((not (& (ego onControl:) cCYAN))
						(NotClose)
					)
					((== showroomState SRshowDone)
						(Print 420 9)
					)
					(else
						(self changeState: 12)
					)
				)
			)
			((Said 'look<in/bolt,(hole<key),door')
				(if (not (& (ego onControl:) cCYAN))
					(NotClose)
				else
					(Print 420 10)
				)
			)
			((Said 'look,look/awning,door')
				(if (not (& (ego onControl:) cCYAN))
					(NotClose)
				else
					(Print 420 11)
				)
			)
			((Said 'board/backstage')
				(Print 420 12)
			)
			((Said '/hemp')
				(Print 420 13)
			)
			((Said 'look>')
				(cond 
					((Said '/lectern')
						(if (== showroomState SRcherriOnPhone)
							(Print 420 14)
						else
							(Print 420 15)
						)
					)
					((Said '/backstage')
						(Print 420 16)
					)
					((Said '/wall')
						(Print 420 0)
						(Print 420 17)
					)
					((Said '/call')
						(if (== showroomState SRcherriOnPhone)
							(Print 420 18)
						else
							(Print 420 17)
						)
					)
					((Said '[/area]')
						(Print
							(Format @msgBuf 420 19
								(cond 
									((== showroomState SRcherriOnPhone)
;;;										{a gorgeous woman in a dressing gown} ;english
										{una preciosa mujer en bata} ;spanish
									)
									((cast contains: aMaitreD)
;;;										{a man standing behind a podium} ;english
										{un hombre de pie detr*s de un estrado} ;spanish
									)
									(else
;;;										{you} ;english
										{ti} ;spanish bien.
									)
								)
							)
						)
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
				(and ;exit room (DrawRect 292 319 183 189)
						(> (event x?) 292) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 133) ;y1
						(< (event y?) 189) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo 324 187 self)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)
			
			
			
			
			
			(if		(and
						(ClickedOnObj aMaitreD (event x?) (event y?))
						(cast contains: aMaitreD);  ;checks that is on the screen
					)

				(switch theCursor
					(996 ;talk
						(event claimed: 1)
						(cond 
							((!= currentStatus egoNORMAL)
								(GoodIdea)
							)
							((not (& (ego onControl:) cRED))
								(Print 420 50)
							)
							((>= showroomState SRstageDoorUnlocked)
								(Print 420 44)
							)
							((>= showroomState SRshowDone)
								(Print 420 45)
							)
							(else
								(if talkedToMaitreD
									;PnCdialogue test
									(= temp0
										(Print
;;;											{What do you want to ask about?} ;english
;;;											#button {Coupon} 1 ;english
;;;											#button {Bar} 2 ;english
;;;											#button {Casino} 3 ;english
;;;											#button {Show} 4 ;english
											
											{&Sobre qu+ quieres preguntar?}
											#button {Cup>n} 1
											#button {Bar} 2
											#button {Casino} 3
											#button {Show} 4
										)
									)
									(switch temp0
										(1 ;coupon
											(cond 
												((!= currentStatus egoNORMAL)
													(GoodIdea)
												)
												((not (& (ego onControl:) cRED))
													(Print 420 47)
												)
												((>= showroomState SRstageDoorUnlocked)
													(Print 420 44)
												)
												((>= showroomState SRshowDone)
													(Print 420 45)
												)
												(else
													(MaitreDScript changeState: 3)
												)
											)
										) 
										(2 ;entertainer 
										(and (not playingAsPatti)
											(cond 
												((!= currentStatus egoNORMAL)
													(GoodIdea)
												)
												((not (& (ego onControl:) cRED))
													(Print 420 37)
												)
												(else
													(Print 420 40)
													(Format @msgBuf 420 41)
													(MaitreDScript changeState: 4)
												)
											)
										)
										)
										(3 ;casino
											(cond 
												((!= currentStatus egoNORMAL)
													(GoodIdea)
												)
												((not (& (ego onControl:) cRED))
													(Print 420 37)
												)
												(else
													(Print 420 42)
													(Format @msgBuf 420 43)
													(MaitreDScript changeState: 4)
												)
											)
										)
										(4 ;show
											(cond 
												((!= currentStatus egoNORMAL)
													(GoodIdea)
												)
												((not (& (ego onControl:) cRED))
													(Print 420 37)
												)
												((>= showroomState SRstageDoorUnlocked)
													(Print 420 44)
												)
												((>= showroomState SRshowDone)
													(Print 420 45)
												)
												(else
													(Format @msgBuf 420 46)
													(MaitreDScript changeState: 4)
												)
											)
										)	
									)
									
								else
									(= talkedToMaitreD TRUE) ;only do intro once
									(Printf 420 51 introductoryPhrase)
									(Print 420 52)
									(Format @msgBuf 420 46)
									(MaitreDScript changeState: 4) ;self to MaitreDScript
								)
							)
						)
					)
					(998 ;look
						(event claimed: 1)
						(Print 420 53)
						(Print 420 54 #at -1 144)
					)	
					(6 ; 20 dollars
						(event claimed: 1)
							(cond 
												((!= currentStatus egoNORMAL)
													(GoodIdea)
												)
												((not (& (ego onControl:) cRED))
													(Print 420 47)
												)
												((>= showroomState SRstageDoorUnlocked)
													(Print 420 44)
												)
												((>= showroomState SRshowDone)
													(Print 420 45)
												)
												(else
													(= itemIcon 900) ;clear menu inv item pic
													(theGame setCursor: 998 (HaveMouse)) ;clear inv cursor, switch to look
													(MaitreDScript changeState: 3)
												)
						)
						(cond
							((!= currentStatus egoNORMAL)
								(GoodIdea)
							)
							((not (ego has: iMoney))
								(Print 420 48)
							)
							((not (& (ego onControl:) cRED))
								(Print 420 47)
							)
							((>= showroomState SRstageDoorUnlocked)
								(Print 420 49)
							)
							(else
								(MaitreDScript changeState: 6)
								

							)

						)										
		
				)(else
					(event claimed: FALSE)
				)
				)
				)		
		
		
				(if
				(and ;cherri door 		
						(> (event x?) 19) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 42) ;x2 
						(> (event y?) 85) ;y1
						(< (event y?) 131) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor	
						(7 ;lands same use
													(cond 
								((== currentStatus egoSHOWGIRL)
									(Print 420 7)
								)
								((or (Btst fScrewedCherri) (>= showroomState SRdone))
									(Print 420 8)
								)
								((not (& (ego onControl:) cCYAN))
									(NotClose)
								)
								((== showroomState SRshowDone)
									(Print 420 9)
								)
								(else
									(= itemIcon 900) ;clear menu inv item pic
									(theGame setCursor: 998 (HaveMouse)) ;clear inv cursor, switch to look	
									(self changeState: 12)
								)
							)
						)					
						(995 ;use,take,knock
							(cond 
								((== currentStatus egoSHOWGIRL)
									(Print 420 7)
									(self changeState: 6)
								)
								((or (Btst fScrewedCherri) (>= showroomState SRdone))
									(Print 420 8)
								)
								((not (& (ego onControl:) cCYAN))
									(NotClose)
								)
								((== showroomState SRshowDone)
									(Print 420 9)
								)
								(else
									(= itemIcon 900) ;clear menu inv item pic
									(theGame setCursor: 998 (HaveMouse)) ;clear inv cursor, switch to look
								
									(self changeState: 12)
								)
							)
						)					
;;;						(998 ;look
;;;							(cond 
;;;								((not (& (ego onControl:) cCYAN))
;;;									(NotClose)
;;;									(ego setMotion: MoveTo 45 132)
;;;								)
;;;								((== currentStatus egoSHOWGIRL)
;;;								
;;;									(self changeState: 6)
;;;								)
;;;								(else
;;;									(Print 420 5)
;;;								)
;;;							)
;;;						)

						 (994 ; cremallera
								(Print 420 4)
								)
							
						 
									
									(else
										(event claimed: FALSE)
									)
								)
							)
							

		
				(if
 					(ClickedOnObj atpPoster1 (event x?) (event y?))
					(event claimed: TRUE)
					(switch theCursor					
						(995 ;use,take
						)
					
						(998 ;look
							(Print 420 0)
						)

						(994 ; cremallera
						)
						(else
							(event claimed: FALSE)
						)
					)
				)

				(if
 					(ClickedOnObj atpPoster2 (event x?) (event y?))
					(event claimed: TRUE)
					(switch theCursor					
						(995 ;use,take
						)
					
						(998 ;look
							(Print 420 0)
						)

						(994 ; cremallera
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
;;;				(if
;;;				(and ;background down		76 240 127 188
;;;						(> (event x?) 76) ;x1 (> (mouseX) (left edge of rectangle))
;;;						(< (event x?) 240) ;x2 
;;;						(> (event y?) 127) ;y1
;;;						(< (event y?) 188) ;y2
;;;					)
;;;					(event claimed: TRUE)
;;;					(switch theCursor					
;;;						
;;;						(998 ;look
;;;						(Print
;;;							(Format @msgBuf 420 19
;;;								(cond 
;;;									((== showroomState SRcherriOnPhone)
;;;;;;										{a gorgeous woman in a dressing gown} ;english
;;;										{una preciosa mujer en bata} ;spanish
;;;									)
;;;									((cast contains: aMaitreD)
;;;;;;										{a man standing behind a podium} ;english
;;;										{un hombre de pie detr*s de un estrado} ;spanish
;;;									)
;;;									(else
;;;;;;										{you} ;english
;;;										{ti} ;spanish bien.
;;;									)
;;;								)
;;;							)
;;;						)
;;;						)
;;;
;;;						(994 ; cremallera
;;;						)
;;;						(else
;;;							(event claimed: FALSE)
;;;						)
;;;					)
;;;				)
;;;
;;;

				(if
				(and ;background		76 240 127 188 1         297
						(> (event x?) 297) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 300) ;x2 
						(> (event y?) 149) ;y1
						(< (event y?) 188) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
;;;						(999 ; WALK
;;;							(ego setMotion: MoveTo 321 177)
;;;						)
;;;					
						(998 ;look
						(Print
							(Format @msgBuf 420 19
								(cond 
									((== showroomState SRcherriOnPhone)
;;;										{a gorgeous woman in a dressing gown} ;english
										{una preciosa mujer en bata} ;spanish
									)
									((cast contains: aMaitreD)
;;;										{a man standing behind a podium} ;english
										{un hombre de pie detr*s de un estrado} ;spanish
									)
									(else
;;;										{you} ;english
										{ti} ;spanish bien.
									)
								)
							)
						)
						)

						(994 ; cremallera
						)
						(else
							(event claimed: FALSE)
						)
					)
				)



				(if
				(and ;phone 		 64 73 73 94
						(> (event x?) 64) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 73) ;x2 
						(> (event y?) 73) ;y1
						(< (event y?) 94) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(995 ;use,take
								(Print 420 3)
						)
					
						(998 ;look
							(if (== showroomState SRcherriOnPhone)
								(Print 420 1)
							else
								(Print 420 2)
							)
						)

						(994 ; cremallera
						)
						(996 ;talk
						)
						(else
							(event claimed: FALSE)
						)
					)
				)

			(if
					(and
						(ClickedOnObj aCherri (event x?) (event y?))
						(cast contains: aCherri);  ;checks that is on the screen
					)
					(event claimed: TRUE)
					(switch theCursor					
						(995 ;use,take
								
						)
					
						(998 ;look
							(cond 
								((!= showroomState SRcherriOnPhone)
									(Print 422 1) ;422 1
								)
								((!= currentStatus egoNORMAL)
									(GoodIdea)
								)
								((!= (aCherri xLast?) (aCherri x?))
									(Print 422 2)
								)
								((not (& (ego onControl:) cMAGENTA))
									(Print 422 3) ;422 3
								)
								(else
									(CherriScript2 changeState: 1)
									(CherriScript2 changeState: 2)
								)
							)
						)
						
						
						
						

						(994 ; cremallera
						)
							(996 ;talk
										

							)
				
						
						(2 ; iKnife
							(Print 420 58)
						)
						(4 ; iGrass
							(Print 420 59)
						)
						(3 ;iWood
							(Print 420 60)
						)
						(11 ; iOrchids
							(Print 420 61)
						)
						(1  ;iCreditCard
						(Print 420 62)
						)
						
						
						
						
						(else
							(event claimed: FALSE)
						)
					)
				)

			)
		)
)


(instance CherriScript2 of Script
	(method (doit)
		(super doit:)
		(if (and (== state 10) (> (ego x?) 280))
			(CherriScript2 cue:)
		)
		(if
			(and
				(== showroomState SRcherriOnPhone)
				(== (aCherri loop?) 4)
				(== (aCherri x?) 82)
				(== (aCherri y?) 124)
			)
			(switch (Random 0 6)
				(0 (aCherri setCel: 0))
				(1 (aCherri setCycle: Forward))
			)
			
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 3 2)
		(switch (= state newState)
			(0
				(if (== showroomState SRknowsAboutDeed)
					(CherriScript2 changeState: 3)
				)
				(if (== showroomState SRshowDone)
					(CherriScript2 changeState: 10)
					(aCherri posn: -20 143 stopUpd:)
				)
			)
			(1
				(Ok)
				(HandsOff)
				(= currentStatus 14)
				(Printf 422 5 introductoryPhrase)
				(aCherri setStep: 0 0 setMotion: Follow ego 222)
				(= seconds 3)
			)
			(2
				(if (not (Btst fMetCherri))
					(Bset fMetCherri)
					(theGame changeScore: 5)
				)
				(Print 422 6)
				(= saveEgoX (ego x?))
				(= saveEgoY (ego y?))
				(= saveEgoLoop (ego loop?))
				(curRoom newRoom: 435)
			)
			(3
				(HandsOff)
				(= seconds 3)
			)
			(4
				(Print 422 7)
				(= seconds 3)
			)
			(5
				(Print 422 8)
				(aCherri
					illegalBits: 0
					ignoreActors: FALSE
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 45 140 self
				)
				(if
					(and
						(> (ego y?) (aCherri y?))
						(< (ego x?) (+ (aCherri x?) 15))
					)
					(ego setCycle: Walk setMotion: MoveTo 97 (ego y?))
				)
			)
			(6
				(theDoor setCycle: EndLoop self)
			)
			(7
				(Print 422 9) 
				(theDoor stopUpd:)
				(aCherri setMotion: MoveTo -20 140 self)
			)
			(8
				(theDoor setCycle: BegLoop self)
			)
			(9
				(soundFX number: 11 loop: 1 play:)
				(= showroomState SRcherriBackstage)
				(theDoor stopUpd:)
				(NormalEgo)
				(ego observeControl: cYELLOW cLMAGENTA)
				(aCherri dispose:)
				(self dispose:)
			)
			(10
				(= seconds 15)
			)
			(11
				(if (< (ego x?) 160)
					(-- state)
					(= cycles 2)
				else
					(HandsOff)
					(theDoor setCycle: EndLoop self)
					(= seconds 0)
				)
			)
			(12
				(theDoor stopUpd:)
				(aCherri
					posn: 13 140
					loop: 0
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 45 140 self
				)
			)
			(13
				(theDoor setCycle: BegLoop self)
			)
			(14
				(soundFX number: 11 loop: 1 play:)
				(theDoor stopUpd:)
				(aCherri setMotion: MoveTo 82 124 self)
			)
			(15
				(aCherri loop: 4)
				(= showroomState 2)
				(HandsOn)
				(ego observeControl: cYELLOW cLMAGENTA)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(!= (event type?) saidEvent)
				(!= showroomState SRcherriOnPhone)
				(event claimed?)
			)
			(return)
		)
		(cond 
			((or (Said 'give/babe') (Said 'give/anyword/babe'))
				(Print 422 0)
			)
			((Said 'look/babe')
				(cond 
					((!= showroomState SRcherriOnPhone)
						(Print 422 1)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((!= (aCherri xLast?) (aCherri x?))
						(Print 422 2)
					)
					((not (& (ego onControl:) cMAGENTA))
						(Print 422 3)
					)
					(else
						(CherriScript2 changeState: 1)
					)
				)
			)
			((and (== showroomState SRcherriOnPhone) (Said '/babe'))
				(Print 422 4)
			)

				
				
			)
				
	)		
)



(instance aMaitreD of Actor
	(properties
		y 105
		x 172
		view 422
		loop 2
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			setScript: MaitreDScript
			ignoreActors:
			stopUpd:
		)
	)
)

(instance MaitreDScript of Script
	(method (changeState newState &tmp [numBuf 5])
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0)
			(1
				(Ok)
				(HandsOff)
				(Print 420 64)
				(aMaitreD setCycle: Forward)
				(= seconds 3)
			)
			(2
				(while (== 0 [correctPassNum passPage])
					(= passPage (Random 1 24))
				)
				(Format @msgBuf 420 65 passPage)
				(MaitreDSays 422 3 0)
				(= numBuf 0)
;;;				(GetInput @numBuf 7 {My pass number is:}) ;english
				(GetInput @numBuf 7 {Mi numero de pase es:}) ;spanish
				(if
					(!=
						(= inputPassNum (ReadNumber @numBuf))
						[correctPassNum passPage]
					)
					(= state 12) ;wrong answer; busted!
				)
				(if debugging
					(Printf 420 66 inputPassNum [correctPassNum passPage] passPage)
				)
				(Format @msgBuf 420 67)
				(MaitreDSays 422 3 0)
				(= seconds 3)
			)
			(3
				(aMaitreD setCel: 0 stopUpd:)
				(Bset fGaveTicketToMaitreD)
				(if (not (Btst fGaveMoneyToMaitreD))
					(Format @msgBuf 420 68)
					(handIcon view: 422 loop: 4)
					(Print @msgBuf
						#at -1 10
;;;						#title {the Maitre d' says} ;English
						#title {el Maitre d' dice} ;Spanish
						#mode teJustCenter
						#icon handIcon
					)
					(= msgBuf 0)
					(HandsOn)
				else
					(Format @msgBuf 420 69)
					(MaitreDSays 422 3 6)
					(self changeState: 8)
				)
			)
			(4
				(HandsOff)
				(aMaitreD setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(5
				(aMaitreD setCel: 0 stopUpd:)
				(if (not msgBuf)
					(Format @msgBuf 420 70)
				)
				(MaitreDSays 422 3 0)
				(HandsOn)
			)
			(6
				(HandsOff)
				(aMaitreD setLoop: 2 setCycle: Forward)
				(= cycles 0)
				(= seconds 3)
			)
			(7
				(aMaitreD setCel: 0 stopUpd:)
				(cond 
					((== ((Inventory at: iMoney) view?) 24)
						(Print 420 71)
						(MaitreDSays 422 3 0)
					)
					((not (Btst fGaveTicketToMaitreD)) ;fix ticket
						(Format @msgBuf 420 72) ; 420 72
						(MaitreDSays 422 3 1) ;422 3 0
						(PutInRoom iMoney)
						(theGame changeScore: 50)
						(Bset fGaveMoneyToMaitreD)
						(HandsOn)
					)
					(else
					
						(HandsOff)
						(Format @msgBuf 420 72) ;420 73
						(MaitreDSays 422 3 1)
						(PutInRoom iMoney)
						(Bset fGaveMoneyToMaitreD)
						(theGame changeScore: 50)
						(= seconds 2)
					)


				)
			)
			(8
				(aMaitreD
					illegalBits: 0
					ignoreActors: TRUE
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo 162 105 self
				)
			)
			(9
				(aMaitreD setMotion: MoveTo 125 109 self)
				(aRope cycleSpeed: 1 setCycle: EndLoop)
			)
			(10
				(aRope stopUpd:)
				(aMaitreD setLoop: 2 setCel: 0 stopUpd:)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 149 114 self
				)
			)
			(11
				(ego setMotion: MoveTo 149 94 self)
			)
			(12
				(curRoom newRoom: 430)
			)
			(13
				(aMaitreD setCel: 0 stopUpd:)
				(Format @msgBuf 420 74)
				(MaitreDSays 422 3 0)
				(= seconds 2)
			)
			(14
				(Format @msgBuf 420 75)
				(MaitreDSays 422 3 0)
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 56
					register: (Format @msgBuf 420 76)
					next: (Format @titleBuf 420 77)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp index)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((or (Said 'ask/pass') (Said 'ask/about/pass'))
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cRED))
						(Print 420 37)
					)
					(else
						(Print 420 38)
						(Format @msgBuf 420 39)
						(self changeState: 4)
					)
				)
			)
			(
			(and (not playingAsPatti) (Said '/entertainer'))
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cRED))
						(Print 420 37)
					)
					(else
						(Print 420 40)
						(Format @msgBuf 420 41)
						(self changeState: 4)
					)
				)
			)
			(
				(or
					(Said '/casino,gambling')
					(Said 'gamble')
					(Said '//casino,gambling')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cRED))
						(Print 420 37)
					)
					(else
						(Print 420 42)
						(Format @msgBuf 420 43)
						(self changeState: 4)
					)
				)
			)
			(
				(or
					(Said 'board/backstage')
					(Said 'ask/show')
					(Said 'ask//show')
					(Said 'look/show')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cRED))
						(Print 420 37)
					)
					((>= showroomState SRstageDoorUnlocked)
						(Print 420 44)
					)
					((>= showroomState SRshowDone)
						(Print 420 45)
					)
					(else
						(Format @msgBuf 420 46)
						(self changeState: 4)
					)
				)
			)
			((Said 'bracelet,use,give,show/pass,book')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cRED))
						(Print 420 47)
					)
					((>= showroomState SRstageDoorUnlocked)
						(Print 420 44)
					)
					((>= showroomState SRshowDone)
						(Print 420 45)
					)
					(else
						(self changeState: 1)
					)
				)
			)
			(
				(or
					(Said 'bracelet,give,show/buck,bill')
					(Said 'buy,tip/man')
					(Said 'bracelet,give,show/man/buck,bill')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (ego has: iMoney))
						(Print 420 48)
					)
					((not (& (ego onControl:) cRED))
						(Print 420 47)
					)
					((>= showroomState SRstageDoorUnlocked)
						(Print 420 49)
					)
					(else
						(self changeState: 6)
					)
				)
			)
			((Said 'address/man')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cRED))
						(Print 420 50)
					)
					((>= showroomState SRstageDoorUnlocked)
						(Print 420 44)
					)
					((>= showroomState SRshowDone)
						(Print 420 45)
					)
					(else
						(Printf 420 51 introductoryPhrase)
						(Print 420 52)
						(Format @msgBuf 420 46)
						(self changeState: 4)
					)
				)
			)
			((Said 'look/man')
				(Print 420 53)
				(Print 420 54
					#at -1 144
				)
			)
			((Said 'give>')
				(= index (inventory saidMe:))
				(event claimed: FALSE)
				(cond 
					((not (& (ego onControl:) cRED))
						(Print 420 55)
					)
					((Said '[/noword]')
						(Print 420 56)
					)
					((not index)
						(Print 420 57)
					)
					((not (index ownedBy: ego))
						(DontHave)
					)
					((== index (inventory at: iKnife))
						(Print 420 58)
					)
					((== index (inventory at: iGrass))
						(Print 420 59)
					)
					((== index (inventory at: iWood))
						(Print 420 60)
					)
					((== index (inventory at: iOrchids))
						(Print 420 61)
					)
					((== index (inventory at: iCreditCard))
						(Print 420 62)
					)
					(else
						(Print 420 63)
					)
				)
				(event claimed: TRUE)
			)
		)
	)
)

(instance atpPhone of PicView
	(properties
		y 96
		x 70
		view 420
		loop 1
		cel 2
		priority 7
	)
)

(instance atpPoster1 of PicView
	(properties
		y 87
		x 224
		view 420
		loop 1
		priority 7
	)
)

(instance atpPoster2 of PicView
	(properties
		y 93
		x 279
		view 420
		loop 1
		cel 1
		priority 7
	)
)

(instance atpPodium of PicView
	(properties
		y 109
		x 166
		view 420
		loop 2
		priority 6
		signal ignrAct
	)
)

(instance aRope of Prop
	(properties
		y 91
		x 106
		view 420
		loop 3
		signal ignrAct
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 6 stopUpd:)
	)
)

(instance aManager of Prop
	(properties
		y 1000
		x 1000
		view 421
	)
	
	(method (init)
		(super init:)
		(self setScript: (ScriptID 421) stopUpd:)
	)
)

(instance aDoor of Prop
	(properties
		y 132
		x 42
		view 420
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance aCherri of Actor
	(properties
		y 124
		x 82
		view 436
		loop 1
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: (ScriptID 422))
	)
)

(instance handIcon of DCIcon)
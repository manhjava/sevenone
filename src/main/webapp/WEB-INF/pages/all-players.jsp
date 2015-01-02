<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="home.title" /></title>
<script src="<c:url value="/resources/scripts/player.js"/>"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/fp-jquery-ui.css"/>" />
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/fpstyle.css"/>" />
</head>
<body>
	<h4>${message}</h4>
	<table class="gridtable">

		<tr>
			<th>Name</th>
			<th>Club</th>
			<th>Weight</th>
			<th>Height</th>
			<th>Position</th>
			<th>Mời tham gia cho vui</th>
		</tr>

		<c:forEach items="${users}" var="userObj">
			<tr>
				<td><a href="#" onclick="viewPlayerDetail('${userObj.username}')">${userObj.username}</a></td>
				<td><a href="<c:url value='/teammanager'/>?teamid=${userObj.team.id}">${userObj.team.name}</a></td>
				<td>${userObj.weight}</td>
				<td>${userObj.height}</td>
				<td>${userObj.position}</td>
				<c:choose>
					<c:when test="${empty userObj.team.name}">
					<td><a href="#" onclick="addPlayer2Team('${userObj.id}')">Thêm vào đội</a></td>
					</c:when>
					<c:otherwise>
					<td></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>

	</table>
	<div id="showplayerinfodiv" class="ui-dialog ui-widget ui-widget-content ui-corner-all ism ui-draggable" tabindex="-1" role="dialog" aria-labelledby="ui-dialog-title-ismEiw" 
	style="display: none; z-index: 9106; outline: 0px; height: auto; width: 750px; top: 169.5px; left: 298.5px;">
<div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
<span class="ui-dialog-title" id="player-name"></span>
<a onclick="closeplayerinfordiv();" class="ui-dialog-titlebar-close ui-corner-all" role="button">
<span class="ui-icon ui-icon-closethick">close</span></a>
</div><div id="ismEiw" class="ismDialog ui-dialog-content ui-widget-content" data-zindex="9100" data-title="Player Information" scrolltop="0" scrollleft="0" style="display: block; width: auto; min-height: 82px; height: auto;">
    <div class="inner">
        <div class="ismDialogContent ismTabs ismEIWTabs ui-tabs ui-widget ui-widget-content ui-corner-all" style="visibility: visible;">
            <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
                <li class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" id="sumaryTab"><a onclick="selectSummaryTab();" title="">Summary</a></li>
                <li class="ui-state-default ui-corner-top" id="historyTab"><a onclick="selectHistoryTab();" title="">History</a></li>
                <li class="ui-state-default ui-corner-top" id="fixturesTab"><a onclick="selectFixturesTab();" title="">Fixtures</a></li>
                <!--li><a href="#ismEIWGraph" title="">Graph</a></li>-->
            </ul>
            <div id="ismEIWSummary" class="ui-tabs-panel ui-widget-content ui-corner-bottom">

                <div class="ismUnit ismSize1of3">
                    <div class="ismSummaryWrapper">
                        <div class="ismPlayerSummary">
                            <figure id="ismEiwImage"><img src="http://cdn.ismfg.net/static/plfpl/img/shirts/photos/15337.jpg" alt="Everton" class="ismEiwImage"></figure>
                            <div class="ismEiwTeam">
                                <span id="ismEiwTeamImage"><img src="http://cdn.ismfg.net/static/plfpl/img/badges/badge_6.png" alt="Everton"></span>
                                <span id="ismEiwTeam">Everton</span>
                            </div>
                            <dl class="clearfix ismStripe">
                                <dt>Vị trí:</dt>
                                <dd id="player-Position"></dd>
                            </dl>
                            <dl class="clearfix ismStripe">
                                <dt>Vị trí:</dt>
                                <dd id="player-Position"></dd>
                            </dl>
                            <dl class="clearfix">
                                <dt>Chiều cao:</dt>
                                <dd id="player-height"></dd>
                            </dl>
                            <dl class="clearfix ismStripe">
                                <dt>Cân nặng:</dt>
                                <dd id="player-weight"></dd>
                            </dl>
                            <dl class="clearfix">
                                <dt>Total score:</dt>
                                <dd id="ismEiwScore">75</dd>
                            </dl>
                        </div>
                    </div>
                </div>

                <div class="ismUnit ismSize2of3 ismLastUnit">

                    <div class="ismLine">
                        <div class="ismUnit ismLastUnit ismSize1of2">
                            <div class="ismMatchesWrapper">
                                <table class="ismTable ismRecentGameweeks" id="ismEiwMatchesPastSummary">
                                    <caption>Recent matches</caption>
                                    <colgroup>
                                        <col class="ismTableCol1">
                                        <col class="ismTableCol2">
                                        <col class="ismTableCol3">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th><abbr title="Gameweek">GW</abbr></th>
                                            <th>Opponent</th>
                                            <th>Points</th>
                                        </tr>
                                    </thead>
                                    <tbody><tr><td>12</td><td>LIV (H)</td><td>1</td></tr><tr><td>13</td><td>STK (H)</td><td>7</td></tr><tr><td>14</td><td>MUN (A)</td><td>8</td></tr></tbody>
                                </table>
                                <table class="ismTable ismUpcomingFixtures" id="ismEiwMatchesFutureSummary">
                                    <caption>Upcoming fixtures</caption>
                                    <colgroup>
                                        <col class="ismTableCol1">
                                        <col class="ismTableCol2">
                                        <col class="ismTableCol3">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th><abbr title="Gameweek">GW</abbr></th>
                                            <th>Opponent</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                    <tbody><tr><td>15</td><td>ARS (A)</td><td>08 Dec 16:00</td></tr><tr><td>16</td><td>FUL (H)</td><td>14 Dec 15:00</td></tr><tr><td>17</td><td>SWA (A)</td><td>22 Dec 16:00</td></tr></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="ismEIWHistory" class="ismLine ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
                <table class="ismTable ismPlayerHistory" id="ismEiwMatchesPast">
                    <caption>Player history</caption>
                    <thead>
                        <tr>
                            
                                <th><abbr title="Date">D</abbr></th>
                            
                                <th><abbr title="Round">R</abbr></th>
                            
                                <th><abbr title="Opponent">O</abbr></th>
                            
                                <th><abbr title="Minutes played">MP</abbr></th>
                            
                                <th><abbr title="Goals scored">GS</abbr></th>
                            
                                <th><abbr title="Assists">A</abbr></th>
                            
                                <th><abbr title="Clean sheets">CS</abbr></th>
                            
                                <th><abbr title="Goals conceded">GC</abbr></th>
                            
                                <th><abbr title="Own goals">OG</abbr></th>
                            
                                <th><abbr title="Penalties saved">PS</abbr></th>
                            
                                <th><abbr title="Penalties missed">PM</abbr></th>
                            
                                <th><abbr title="Yellow cards">YC</abbr></th>
                            
                                <th><abbr title="Red cards">RC</abbr></th>
                            
                                <th><abbr title="Saves">S</abbr></th>
                            
                                <th><abbr title="Bonus">B</abbr></th>
                            
                                <th><abbr title="EA SPORTS PPI">ESP</abbr></th>
                            
                                <th><abbr title="Bonus Points System">BPS</abbr></th>
                            
                                <th><abbr title="Net transfers">NT</abbr></th>
                            
                                <th><abbr title="Value">V</abbr></th>
                            
                                <th><abbr title="Points">P</abbr></th>
                            
                        </tr>
                    </thead>
                    <tbody class="ismHistoryThisSeason"><tr><td>17 Aug 15:00</td><td>1</td><td>NOR(A) 2-2</td><td>90</td><td>0</td><td>0</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>5</td><td>3</td><td>0</td><td>£5.5</td><td>1</td></tr><tr><td>24 Aug 15:00</td><td>2</td><td>WBA(H) 0-0</td><td>90</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>2</td><td>0</td><td>8</td><td>7</td><td>-5081</td><td>£5.5</td><td>6</td></tr><tr><td>31 Aug 15:00</td><td>3</td><td>CAR(A) 0-0</td><td>90</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>1</td><td>0</td><td>7</td><td>7</td><td>-176</td><td>£5.5</td><td>6</td></tr><tr><td>14 Sep 17:30</td><td>4</td><td>CHE(H) 1-0</td><td>90</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>5</td><td>0</td><td>18</td><td>8</td><td>-5211</td><td>£5.5</td><td>7</td></tr><tr><td>21 Sep 15:00</td><td>5</td><td>WHU(A) 3-2</td><td>90</td><td>0</td><td>0</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>12</td><td>3</td><td>723</td><td>£5.5</td><td>1</td></tr><tr><td>30 Sep 20:00</td><td>6</td><td>NEW(H) 3-2</td><td>90</td><td>0</td><td>1</td><td>0</td><td>2</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>4</td><td>0</td><td>23</td><td>8</td><td>-2385</td><td>£5.5</td><td>5</td></tr><tr><td>05 Oct 12:45</td><td>7</td><td>MCI(A) 1-3</td><td>90</td><td>0</td><td>0</td><td>0</td><td>3</td><td>1</td><td>1</td><td>0</td><td>1</td><td>0</td><td>4</td><td>0</td><td>5</td><td>5</td><td>-2808</td><td>£5.5</td><td>4</td></tr><tr><td>19 Oct 15:00</td><td>8</td><td>HUL(H) 2-1</td><td>90</td><td>0</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>12</td><td>3</td><td>418</td><td>£5.5</td><td>2</td></tr><tr><td>26 Oct 15:00</td><td>9</td><td>AVL(A) 2-0</td><td>90</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>1</td><td>0</td><td>1</td><td>0</td><td>3</td><td>3</td><td>15</td><td>12</td><td>-2296</td><td>£5.5</td><td>14</td></tr><tr><td>03 Nov 13:30</td><td>10</td><td>TOT(H) 0-0</td><td>90</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td><td>0</td><td>1</td><td>0</td><td>6</td><td>0</td><td>12</td><td>8</td><td>9023</td><td>£5.5</td><td>7</td></tr><tr><td>09 Nov 15:00</td><td>11</td><td>CRY(A) 0-0</td><td>90</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>2</td><td>0</td><td>8</td><td>7</td><td>23393</td><td>£5.5</td><td>6</td></tr><tr><td>23 Nov 12:45</td><td>12</td><td>LIV(H) 3-3</td><td>90</td><td>0</td><td>0</td><td>0</td><td>3</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>2</td><td>0</td><td>8</td><td>2</td><td>15868</td><td>£5.5</td><td>1</td></tr><tr><td>30 Nov 15:00</td><td>13</td><td>STK(H) 4-0</td><td>90</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>3</td><td>0</td><td>16</td><td>8</td><td>14825</td><td>£5.6</td><td>7</td></tr><tr><td>04 Dec 19:45</td><td>14</td><td>MUN(A) 1-0</td><td>90</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>6</td><td>0</td><td>19</td><td>9</td><td>11492</td><td>£5.6</td><td>8</td></tr></tbody>
                    <tbody class="ismHistoryTotals">
                        <tr>
                            <td class="ismPointsTotal" colspan="3" data-empty="&lt;td colspan=&quot;16&quot;&gt;&lt;/td&gt;">Totals</td><td>1260</td><td>0</td><td>1</td><td>8</td><td>13</td><td>1</td><td>2</td><td>0</td><td>3</td><td>0</td><td>38</td><td>3</td><td>168</td><td>90</td><td></td><td></td><td>75</td>
                        </tr>
                    </tbody>
                    <tbody class="ismHistoryPastSeasons"><tr><td colspan="3">2012/13</td><td>3240</td><td>0</td><td>0</td><td>10</td><td>39</td><td>1</td><td>0</td><td>0</td><td>0</td><td>0</td><td>98</td><td>0</td><td>409</td><td>0</td><td>&nbsp;</td><td>£5.3</td><td>117</td></tr><tr><td colspan="3">2011/12</td><td>3420</td><td>1</td><td>0</td><td>12</td><td>40</td><td>0</td><td>2</td><td>0</td><td>1</td><td>0</td><td>88</td><td>3</td><td>428</td><td>0</td><td>&nbsp;</td><td>£5.4</td><td>151</td></tr><tr><td colspan="3">2010/11</td><td>3420</td><td>0</td><td>0</td><td>9</td><td>45</td><td>0</td><td>0</td><td>0</td><td>3</td><td>0</td><td>108</td><td>3</td><td>0</td><td>0</td><td>&nbsp;</td><td>£5.3</td><td>124</td></tr><tr><td colspan="3">2009/10</td><td>3420</td><td>0</td><td>0</td><td>0</td><td>49</td><td>0</td><td>2</td><td>0</td><td>2</td><td>0</td><td>105</td><td>3</td><td>0</td><td>0</td><td>&nbsp;</td><td>£5.6</td><td>137</td></tr><tr><td colspan="3">2008/09</td><td>3420</td><td>0</td><td>0</td><td>0</td><td>37</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>116</td><td>0</td><td>0</td><td>0</td><td>&nbsp;</td><td>£5.2</td><td>159</td></tr><tr><td colspan="3">2007/08</td><td>3240</td><td>0</td><td>1</td><td>0</td><td>30</td><td>0</td><td>0</td><td>0</td><td>1</td><td>0</td><td>95</td><td>0</td><td>0</td><td>0</td><td>&nbsp;</td><td>£5.2</td><td>147</td></tr><tr><td colspan="3">2006/07</td><td>3240</td><td>0</td><td>0</td><td>0</td><td>29</td><td>0</td><td>1</td><td>0</td><td>1</td><td>0</td><td>138</td><td>3</td><td>0</td><td>0</td><td>&nbsp;</td><td>£5.5</td><td>164</td></tr></tbody>
                </table>
            </div>
            <!-- // end EIW history -->
            <div id="ismEIWFixtures" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
                <table class="ismTable ismEIWFixtures" id="ismEiwMatchesFuture">
                    <caption>Fixtures</caption>
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Gameweek</th>
                            <th>Opponent</th>
                        </tr>
                    </thead>
                    <tbody><tr><td>08 Dec 16:00</td><td>Gameweek 15</td><td>Arsenal (A)</td></tr><tr><td>14 Dec 15:00</td><td>Gameweek 16</td><td>Fulham (H)</td></tr><tr><td>22 Dec 16:00</td><td>Gameweek 17</td><td>Swansea (A)</td></tr><tr><td>26 Dec 15:00</td><td>Gameweek 18</td><td>Sunderland (H)</td></tr><tr><td>29 Dec 13:30</td><td>Gameweek 19</td><td>Southampton (H)</td></tr><tr><td>01 Jan 15:00</td><td>Gameweek 20</td><td>Stoke City (A)</td></tr><tr><td>11 Jan 15:00</td><td>Gameweek 21</td><td>Norwich (H)</td></tr><tr><td>20 Jan 20:00</td><td>Gameweek 22</td><td>West Brom (A)</td></tr><tr><td>28 Jan 20:00</td><td>Gameweek 23</td><td>Liverpool (A)</td></tr><tr><td>01 Feb 15:00</td><td>Gameweek 24</td><td>Aston Villa (H)</td></tr><tr><td>08 Feb 15:00</td><td>Gameweek 25</td><td>Tottenham (A)</td></tr><tr><td>12 Feb 19:45</td><td>Gameweek 26</td><td>Crystal Palace (H)</td></tr><tr><td>22 Feb 15:00</td><td>Gameweek 27</td><td>Chelsea (A)</td></tr><tr><td>01 Mar 15:00</td><td>Gameweek 28</td><td>West Ham (H)</td></tr><tr><td>08 Mar 15:00</td><td>Gameweek 29</td><td>Newcastle (A)</td></tr><tr><td>15 Mar 15:00</td><td>Gameweek 30</td><td>Cardiff City (H)</td></tr><tr><td>22 Mar 15:00</td><td>Gameweek 31</td><td>Swansea (H)</td></tr><tr><td>29 Mar 15:00</td><td>Gameweek 32</td><td>Fulham (A)</td></tr><tr><td>05 Apr 15:00</td><td>Gameweek 33</td><td>Arsenal (H)</td></tr><tr><td>12 Apr 15:00</td><td>Gameweek 34</td><td>Sunderland (A)</td></tr><tr><td>19 Apr 15:00</td><td>Gameweek 35</td><td>Man Utd (H)</td></tr><tr><td>26 Apr 15:00</td><td>Gameweek 36</td><td>Southampton (A)</td></tr><tr><td>03 May 15:00</td><td>Gameweek 37</td><td>Man City (H)</td></tr><tr><td>11 May 15:00</td><td>Gameweek 38</td><td>Hull City (A)</td></tr></tbody>
                </table>
            </div>
            <!--<div id="ismEIWGraph">
                <p>Graph</p>
            </div>-->
            <p class="ismPBEALogo"><img src="http://cdn.ismfg.net/static/plfpl/img/ea-logo-small.png" alt="Powered by EA Games"></p>
        </div>
    </div>
</div></div>
</body>
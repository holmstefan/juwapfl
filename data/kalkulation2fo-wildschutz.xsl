<?xml version="1.0" encoding="ISO-8859-1"?>
<!--
  Copyright 2023 Stefan Holm
  
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
      http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
	<xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
	<xsl:include href="data/kalkulation2fo-templates.xsl"/>
	<xsl:param name="versionParam" select="'1.0'" />
					
	<xsl:template match="ergebnis">
		<fo:block font-size="10pt">
			<fo:table table-layout="fixed" width="100%"	border-collapse="separate">
				<fo:table-column column-width="2.8cm" />
				<fo:table-column column-width="1.775cm" />
				<fo:table-column column-width="1.775cm" />
				<fo:table-column column-width="1.775cm" />
				<fo:table-column column-width="1.775cm" />
				<fo:table-column column-width="1.775cm" />
				<fo:table-column column-width="1.775cm" />
				<fo:table-column column-width="1.775cm" />
				<fo:table-column column-width="1.775cm" />

				<fo:table-body>
					<fo:table-row>
						<fo:table-cell>
							<fo:block font-weight="bold"> <xsl:value-of select="../pdftitles/ergebnis"/> </fo:block>
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row>
						<fo:table-cell>
							<fo:block>&#160;</fo:block>
						</fo:table-cell>
						
						<fo:table-cell text-align="center" number-columns-spanned="4">
							<fo:block>
							<xsl:value-of select="../pdftitles/zeiten"/>
							</fo:block>
						</fo:table-cell>
						
						<fo:table-cell text-align="center" number-columns-spanned="4">
							<fo:block>
							<xsl:value-of select="../pdftitles/kosten"/>&#160;[<xsl:value-of select="../info/currency"/>]
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row>
						<fo:table-cell padding-top="2pt" padding-bottom="2pt">
							<fo:block>&#160;</fo:block>
						</fo:table-cell>
						
						<fo:table-cell text-align="right">
							<fo:block><xsl:value-of select="../pdftitles/aufbau"/></fo:block>
						</fo:table-cell>
						
						<fo:table-cell text-align="right">
							<fo:block><xsl:value-of select="../pdftitles/unterhalt"/></fo:block>
						</fo:table-cell>
						
						<fo:table-cell text-align="right">
							<fo:block><xsl:value-of select="../pdftitles/abbau"/></fo:block>
						</fo:table-cell>
						
						<fo:table-cell text-align="right">
							<fo:block><xsl:value-of select="../pdftitles/gesamt"/></fo:block>
						</fo:table-cell>
						
						<fo:table-cell text-align="right">
							<fo:block><xsl:value-of select="../pdftitles/aufbau"/></fo:block>
						</fo:table-cell>
						
						<fo:table-cell text-align="right">
							<fo:block><xsl:value-of select="../pdftitles/unterhalt"/></fo:block>
						</fo:table-cell>
						
						<fo:table-cell text-align="right">
							<fo:block><xsl:value-of select="../pdftitles/abbau"/></fo:block>
						</fo:table-cell>
						
						<fo:table-cell text-align="right">
							<fo:block><xsl:value-of select="../pdftitles/gesamt"/></fo:block>
						</fo:table-cell>
					</fo:table-row>
					
					<xsl:apply-templates select="*" />
	
				</fo:table-body>
			</fo:table>
		</fo:block>	
	</xsl:template>

</xsl:stylesheet>
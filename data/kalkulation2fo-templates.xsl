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
			
	<!-- ========================= -->
	<!-- root element: kalkulation -->
	<!-- ========================= -->
	<xsl:template match="kalkulation">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" font-family="Helvetica">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="0.8cm" margin-left="2cm" margin-right="2cm">
					<fo:region-body region-name="xsl-region-body" margin-bottom="2cm" margin-top="0cm"/>>
<!-- 					<fo:region-before region-name="xsl-region-before" extent="0cm"/> -->
            		<fo:region-after region-name="xsl-region-after" extent="1cm"/>
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="simpleA4">
			
<!-- 				<fo:static-content flow-name="xsl-region-before"> -->
<!-- 						<fo:block>header</fo:block> -->
<!-- 				</fo:static-content> -->
				
				<fo:static-content flow-name="xsl-region-after">
						<fo:block font-size="8pt" text-align="right">
							<xsl:value-of select="info/creationdate"/>
						</fo:block>
				</fo:static-content>
        
				<fo:flow flow-name="xsl-region-body">
				
					<fo:block font-size="16pt" font-weight="bold" space-after="5mm">
						<xsl:value-of select="pdftitles/haupttitel"/>&#160;<xsl:value-of select="modellname" />
					</fo:block>
					
					<fo:block font-size="10pt">
						<fo:table table-layout="fixed" width="100%"	border-collapse="separate">
							<fo:table-column column-width="8cm" />
							<fo:table-column column-width="0.5cm" />
							<fo:table-column column-width="9.5cm" />
							<fo:table-body>

								<xsl:if test="info/arbeitsort">
									<fo:table-row>
										<fo:table-cell>
											<fo:block font-weight="bold"> <xsl:value-of select="pdftitles/arbeitsort"/> </fo:block>
										</fo:table-cell>
										
										<fo:table-cell>
											<fo:block>&#160;</fo:block>
										</fo:table-cell>
									
										<fo:table-cell>
											<fo:block> <xsl:value-of select="info/arbeitsort" /> </fo:block>
										</fo:table-cell>
									</fo:table-row>

									<fo:table-row>
										<fo:table-cell>
											<fo:block>&#160;</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:if>
								
								<xsl:apply-templates select="eingaben/section" />

								<fo:table-row>
									<fo:table-cell>
										<fo:block>&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</fo:table-body>
						</fo:table>
					</fo:block>
								
					<xsl:apply-templates select="ergebnis" />
					
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	
	
	<!-- ========================= -->
	<!--  child element templates  -->
	<!-- ========================= -->
	
	<xsl:template match="section">
		<fo:table-row>
			<fo:table-cell padding-bottom="2pt">
				<fo:block font-weight="bold">
					<xsl:value-of select="@name" />
				</fo:block>
			</fo:table-cell>
		</fo:table-row>
		
		<xsl:apply-templates select="entry" />

		<fo:table-row>
			<fo:table-cell>
				<fo:block>&#160;</fo:block>
			</fo:table-cell>
		</fo:table-row>	
	</xsl:template>
	
	<xsl:template match="entry">
		<fo:table-row>
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="label" />
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell>
				<fo:block>&#160;</fo:block>
			</fo:table-cell>
			
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="value" />
				</fo:block>
			</fo:table-cell>
		</fo:table-row>
	</xsl:template>
	
	<xsl:template match="ergebniszeile">
		<fo:table-row>
			<xsl:if test="spalte0">
				<fo:table-cell text-align="right" padding-top="5pt">
					<fo:block> <xsl:value-of select="spalte0" /> </fo:block>
				</fo:table-cell>
			</xsl:if>
					
			<xsl:if test="spalte1">
				<fo:table-cell text-align="right" padding-top="5pt">
					<fo:block> <xsl:value-of select="spalte1" /> </fo:block>
				</fo:table-cell>
			</xsl:if>
					
			<xsl:if test="spalte2">
				<fo:table-cell text-align="right" padding-top="5pt">
					<xsl:if test="spalte2/b">
						<fo:block font-weight="bold">
							<xsl:value-of select="spalte2" />
						</fo:block>
					</xsl:if>
					<xsl:if test="not(spalte2/b)">
						<fo:block>
							<xsl:value-of select="spalte2" />
						</fo:block>
					</xsl:if>
				</fo:table-cell>
			</xsl:if>
					
			<xsl:if test="spalte3">
				<fo:table-cell text-align="right" padding-top="5pt">
					<xsl:if test="spalte3/b">
						<fo:block font-weight="bold">
							<xsl:value-of select="spalte3" />
						</fo:block>
					</xsl:if>
					<xsl:if test="not(spalte3/b)">
						<fo:block>
							<xsl:value-of select="spalte3" />
						</fo:block>
					</xsl:if>
				</fo:table-cell>
			</xsl:if>
					
			<xsl:if test="spalte4">
				<fo:table-cell text-align="right" padding-top="5pt">
					<xsl:if test="spalte4/b">
						<fo:block font-weight="bold">
							<xsl:value-of select="spalte4" />
						</fo:block>
					</xsl:if>
					<xsl:if test="not(spalte4/b)">
						<fo:block>
							<xsl:value-of select="spalte4" />
						</fo:block>
					</xsl:if>
				</fo:table-cell>
			</xsl:if>
					
			<xsl:if test="spalte5">
				<fo:table-cell text-align="right" padding-top="5pt">
					<xsl:if test="spalte5/b">
						<fo:block font-weight="bold">
							<xsl:value-of select="spalte5" />
						</fo:block>
					</xsl:if>
					<xsl:if test="not(spalte5/b)">
						<fo:block>
							<xsl:value-of select="spalte5" />
						</fo:block>
					</xsl:if>
				</fo:table-cell>
			</xsl:if>
					
			<xsl:if test="spalte6">
				<fo:table-cell text-align="right" padding-top="5pt">
					<xsl:if test="spalte6/b">
						<fo:block font-weight="bold">
							<xsl:value-of select="spalte6" />
						</fo:block>
					</xsl:if>
					<xsl:if test="not(spalte6/b)">
						<fo:block>
							<xsl:value-of select="spalte6" />
						</fo:block>
					</xsl:if>
				</fo:table-cell>
			</xsl:if>	
					
			<xsl:if test="spalte7">
				<fo:table-cell text-align="right" padding-top="5pt">
					<xsl:if test="spalte7/b">
						<fo:block font-weight="bold">
							<xsl:value-of select="spalte7" />
						</fo:block>
					</xsl:if>
					<xsl:if test="not(spalte7/b)">
						<fo:block>
							<xsl:value-of select="spalte7" />
						</fo:block>
					</xsl:if>
				</fo:table-cell>
			</xsl:if>	
					
			<xsl:if test="spalte8">
				<fo:table-cell text-align="right" padding-top="5pt">
					<xsl:if test="spalte8/b">
						<fo:block font-weight="bold">
							<xsl:value-of select="spalte8" />
						</fo:block>
					</xsl:if>
					<xsl:if test="not(spalte8/b)">
						<fo:block>
							<xsl:value-of select="spalte8" />
						</fo:block>
					</xsl:if>
				</fo:table-cell>
			</xsl:if>	
					
			<xsl:if test="spalte9">
				<fo:table-cell text-align="right" padding-top="5pt">
					<xsl:if test="spalte9/b">
						<fo:block font-weight="bold">
							<xsl:value-of select="spalte9" />
						</fo:block>
					</xsl:if>
					<xsl:if test="not(spalte9/b)">
						<fo:block>
							<xsl:value-of select="spalte9" />
						</fo:block>
					</xsl:if>
				</fo:table-cell>
			</xsl:if>	
					
			<xsl:if test="spalte10">
				<fo:table-cell text-align="right" padding-top="5pt">
					<xsl:if test="spalte10/b">
						<fo:block font-weight="bold">
							<xsl:value-of select="spalte10" />
						</fo:block>
					</xsl:if>
					<xsl:if test="not(spalte10/b)">
						<fo:block>
							<xsl:value-of select="spalte10" />
						</fo:block>
					</xsl:if>
				</fo:table-cell>
			</xsl:if>
		</fo:table-row>
	</xsl:template>
	
	<xsl:template match="leerzeile">
		<fo:table-row>
			<fo:table-cell>
				<fo:block>&#160;</fo:block>
			</fo:table-cell>
		</fo:table-row>
	</xsl:template>
</xsl:stylesheet>
package Annotation.orther.com;

import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 
 * http://www.cnblogs.com/peida/archive/2013/04/23/3036035.html
 * 1.ע�����:Annotion(ע��)��һ�������java�ӿڣ��������ͨ����������ȡָ������Ԫ�ص�Annotion����Ȼ��ͨ��Annotion��������ȡע�������Ԫ���ݡ�
 		 �����Ĺ���Annotation����Ӱ���������ִ�У��������ӡ�ɾ�� Annotation�����붼ʼ����һ��ִ��
 	
 	2.Ԫ����	�������������ĵ������ٴ���������ԣ�ִ�б���ʱ��ʽ��飬�������е������ļ���
 			Java��Ԫ�����Ա�ǩ����ʽ������Java�����У�Ԫ���ݱ�ǩ�Ĵ��ڲ���Ӱ��������ı����ִ�У���ֻ�Ǳ����������������ļ�����������ʱ֪�������д����������Ϣ��
 			
 			1. ��д�ĵ���ͨ���������ʶ��Ԫ���������ĵ�
��������		2. ���������ͨ���������ʶ��Ԫ���ݶԴ�����з���
��������		3. �����飺ͨ���������ʶ��Ԫ�����ñ�������ʵ�ֻ����ı�����	 

		��һ��Ԫ�����Ա�ǩ����ʽ������Java�����С�
���������ڶ���Ԫ������������Ϣ�����Ͱ�ȫ�ģ���Ԫ�����ڲ����ֶζ�������ȷ���͵ġ�
��������������Ԫ������Ҫ������֮��Ĺ��߶���Ĵ����������������ĳ��򲿼���
�����������ģ�Ԫ���ݿ���ֻ������JavaԴ���뼶��Ҳ���Դ����ڱ���֮���Class�ļ��ڲ���
 	3.ע��ķ��ࣺ

����		����ע������ĸ��������ǿ��Խ�ע���Ϊ���ࣺ
��������		1.���ע��:һ��û�г�Ա�����Annotation���ͱ���Ϊ���ע�⡣����Annotation���ͽ�ʹ������Ĵ��������Ϊ�����ṩ��Ϣ����������ϵͳע��@Override;
��������		2.��ֵע��
��������		3.����ע�⡡��

����		����ע��ʹ�÷�������;�����ǿ��Խ�Annotation��Ϊ���ࣺ
��������		1.JDK����ϵͳע��
					@Override���������δ˷��������˸���ķ���;
��������				@Deprecated�����������Ѿ���ʱ�ķ���;
��������				@SuppressWarnnings:����֪ͨjava��������ֹ�ض��ı��뾯�档
						1.deprecation��ʹ���˲��޳�ʹ�õ���򷽷�ʱ�ľ��棻
��������					2.unchecked��ִ����δ����ת��ʱ�ľ��棬���統ʹ�ü���ʱû���÷��� (Generics) ��ָ�����ϱ��������; 
��������					3.fallthrough���� Switch �����ֱ��ͨ����һ�������û�� Break ʱ�ľ���;
��������					4.path������·����Դ�ļ�·�������в����ڵ�·��ʱ�ľ���; 
��������					5.serial�����ڿ����л�������ȱ�� serialVersionUID ����ʱ�ľ���; 
��������					6.finally���κ� finally �Ӿ䲻���������ʱ�ľ���; 
��������					7.all������������������ľ��档
��������		2.Ԫע��
��������		3.�Զ���ע��	 
 		 
 		 
 * Ԫע�⣺

����Ԫע������þ��Ǹ���ע������ע�⡣Java5.0������4����׼��meta-annotation���ͣ����Ǳ������ṩ������ annotation������˵����Java5.0�����Ԫע�⣺
��������1.@Target,ע��ʹ�÷�Χ
��������2.@Retention,ע����������
��������3.@Documented,���ע��  javadoc�ĵ�api
��������4.@Inherited��������̳и����е�ע�⡣
������Щ���ͺ�������֧�ֵ�����java.lang.annotation���п����ҵ����������ǿ�һ��ÿ��Ԫע������ú���Ӧ�ֲ�����ʹ��˵����
		
		
		
		
@Target��
		
		������@Target˵����Annotation�����εĶ���Χ��Annotation�ɱ����� packages��types���ࡢ�ӿڡ�ö�١�Annotation���ͣ������ͳ�Ա�����������췽������Ա������ö��ֵ�������������ͱ��ر�������ѭ��������catch����������Annotation���͵�������ʹ����target�ɸ������������ε�Ŀ�ꡣ
		
		�������ã���������ע���ʹ�÷�Χ��������������ע���������ʲô�ط���
		
		����ȡֵ(ElementType)�У�
		
		��������1.CONSTRUCTOR:��������������
		��������2.FIELD:����������
		��������3.LOCAL_VARIABLE:���������ֲ�����
		��������4.METHOD:������������
		��������5.PACKAGE:����������
		��������6.PARAMETER:������������
		��������7.TYPE:���������ࡢ�ӿ�(����ע������) ��enum����

@Retention��

		����@Retention�����˸�Annotation��������ʱ�䳤�̣�
				ĳЩAnnotation��������Դ�����У�����������������
				����һЩȴ��������class�ļ��У�������class�ļ��е�Annotation���ܻᱻ��������ԣ�
				����һЩ��class��װ��ʱ������ȡ����ע�Ⲣ��Ӱ��class��ִ�У���ΪAnnotation��class��ʹ�����Ǳ�����ģ���
				ʹ�����meta-Annotation���Զ� Annotation�ġ��������ڡ����ơ�
		
		�������ã���ʾ��Ҫ��ʲô���𱣴��ע����Ϣ����������ע����������ڣ�������������ע����ʲô��Χ����Ч��
		
		����ȡֵ��RetentionPoicy���У�
		
		��������1.SOURCE:��Դ�ļ�����Ч����Դ�ļ�������
		��������2.CLASS:��class�ļ�����Ч����class������
		��������3.RUNTIME:������ʱ��Ч��������ʱ������
		
		����Retention meta-annotation������Ψһ��value��Ϊ��Ա������ȡֵ����java.lang.annotation.RetentionPolicy��ö������ֵ������ʵ�����£�

@Documented:

����@Documented���������������͵�annotationӦ�ñ���Ϊ����ע�ĳ����Ա�Ĺ���API����˿��Ա�����javadoc����Ĺ����ĵ�����Documented��һ�����ע�⣬û�г�Ա��
 
@Inherited�� 

����@Inherited Ԫע����һ�����ע�⣬@Inherited������ĳ������ע�������Ǳ��̳еġ����һ��ʹ����@Inherited���ε�annotation���ͱ�����һ��class�������annotation�������ڸ�class�����ࡣ

����ע�⣺@Inherited annotation�����Ǳ���ע����class���������̳С��ಢ��������ʵ�ֵĽӿڼ̳�annotation�������������������صķ����̳�annotation��

������@Inherited annotation���ͱ�ע��annotation��Retention��RetentionPolicy.RUNTIME������API��ǿ�����ּ̳��ԡ��������ʹ��java.lang.reflectȥ��ѯһ��@Inherited annotation���͵�annotationʱ����������齫չ�����������class���丸�ֱ࣬������ָ����annotation���ͱ����֣����ߵ�����̳нṹ�Ķ��㡣 

�Զ���ע�⣺Definition.java��

����ʹ��@interface�Զ���ע��ʱ���Զ��̳���java.lang.annotation.Annotation�ӿڣ��ɱ�������Զ��������ϸ�ڡ��ڶ���ע��ʱ�����ܼ̳�������ע���ӿڡ�@interface��������һ��ע�⣬���е�ÿһ������ʵ������������һ�����ò��������������ƾ��ǲ��������ƣ�����ֵ���;��ǲ��������ͣ�����ֵ����ֻ���ǻ������͡�Class��String��enum��������ͨ��default������������Ĭ��ֵ��


 * 
 *ע��Table ��������ע���ࡢ�ӿ�(����ע������) ��enum����,��ע��NoDBColumn��������ע����ĳ�Ա������
 */
@Target(ElementType.TYPE)
public @interface AnnotationXueXi {
    /**
     * ���ݱ�����ע�⣬Ĭ��ֵΪ������
     * @return
     */
    public String tableName() default "className";
}

@Target(ElementType.FIELD)
@interface NoDBColumn {

}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
//Columnע��ĵ�RetentionPolicy������ֵ��RUTIME,����ע�⴦��������ͨ�����䣬��ȡ����ע�������ֵ���Ӷ�ȥ��һЩ����ʱ���߼�����
@Documented
@Inherited
@interface Column {
    public String name() default "fieldName";
    public String setFuncName() default "setField";
    public String getFuncName() default "getField"; 
    public boolean defaultDBValue() default false;
}


import {GetServerSideProps, GetServerSidePropsContext, GetServerSidePropsResult} from 'next'
import {parseCookies} from 'nookies'

//funcao para paginas que so pode ser acessadas por visitantes
export function canSSRGuest<P extends { [key: string]: any; }>(fn: GetServerSideProps<P>){
    return async (context: GetServerSidePropsContext): Promise<GetServerSidePropsResult<P>> => {

        const cookies = parseCookies(context);

        if(cookies['@auth.token']){
            return{
                redirect:{
                    destination: '/dashboard',
                    permanent: false,
                }
            }
        }

        return await fn(context);
    }
}